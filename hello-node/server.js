const express = require('express');
const { sleep } = require('sleep');

const app = express();

let couter = 1;

function pi_monte_carlo(iterations) {
    sleep(1);
    let pi = 0;
    let iterator = sequence();

    for(let i = 0; i < iterations; i++){
        pi += 4 /  iterator.next().value;
        pi -= 4 / iterator.next().value;
    }

    function* sequence() {
      let i = 1;
      while(true){
        yield i;
        i += 2;
      }
    }

    return pi;
}

app.get('/hello', (req, res) => {
    res.send('Hello ' + '(' + couter++ +')');
});
app.get('/naptime', async (req, res) => {
    const answer = await pi_monte_carlo(20000);
    res.send('your slice of pi is ' + answer);
});
app.listen(8080, () => console.log(`Example app listening on port 8080!`))