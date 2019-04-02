// ./app.js

const vm = new Vue({
    el: '#app',
    data: {
        results: []
    },
    mounted() {
        axios.get("/1").then(response => {this.results = response.data.results})
    }
});