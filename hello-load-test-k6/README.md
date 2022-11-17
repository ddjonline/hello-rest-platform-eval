# hello-load-test-k6

## Prerequists

Install [K6](https://k6.io/)

# Run the test

With one of the platform options up and running, in a separate terminal issue the following command.

```shell
k6 run -e HTTP_PROTO=http -e BASE_URL=localhost:8080 main.js
```
