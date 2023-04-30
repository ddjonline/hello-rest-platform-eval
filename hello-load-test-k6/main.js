import http from "k6/http";
import ws from 'k6/ws';
import { check, fail } from 'k6';
import { sleep } from 'k6';
import { Rate } from 'k6/metrics';
import { FormData } from 'https://jslib.k6.io/formdata/0.0.2/index.js';

export const options = {
    thresholds: {
        'http_req_duration': ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
    scenarios: {
        darren_scenario_naptime: {
            executor: 'per-vu-iterations',
            exec: "testNaptime",
            startTime: '0s',
            gracefulStop: '1m',
            vus: 200,
            iterations: 1,
            maxDuration: '5m',
            env: { URL: 'naptime' }
        },
        darren_scenario_hello: {
            executor: 'per-vu-iterations',
            exec: "testHello",
            startTime: '1.5s', // let the naptime requests stack up for 2 seconds and then attempt hello
            gracefulStop: '1m',
            vus: 50,
            iterations: 100,
            maxDuration: '5m',
            env: { URL: 'hello' }
        }
    },
    summaryTrendStats: ['avg', 'min', 'med', 'max', 'p(90)', 'p(95)', 'p(99)', 'count'],
};

for (let key in options.scenarios) {
    // Each scenario automaticall tags the metrics it generates with its own name
    let thresholdName = `http_req_duration{scenario:${key}}`;
    // Check to prevent us from overwriting a threshold that already exists
    if (!options.thresholds[thresholdName]) {
        options.thresholds[thresholdName] = [];
    }
    // 'max>=0' is a bogus condition that will always be fulfilled
    options.thresholds[thresholdName].push('max>=0');
}

const GET_HEADERS = {
    headers: {
        'Accept': 'text/plain'
    }
};

export function testHello() {
    // const response = http.get(`${__ENV.HTTP_PROTO}://${__ENV.BASE_URL}/${__ENV.URL}`, null, GET_HEADERS);
    const response = http.get(`${__ENV.HTTP_PROTO}://${__ENV.BASE_URL}/hello`, null, GET_HEADERS);
}

export function testNaptime() {
    const response = http.get(`${__ENV.HTTP_PROTO}://${__ENV.BASE_URL}/naptime`, null, GET_HEADERS);
}