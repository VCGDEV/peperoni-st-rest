function fn() {
    // run with:  ./gradlew integrationTest "-Purl=http://domain:port/peperoni-st-rest"
    var config = {
        baseUrl: karate.properties['url'] || 'http://localhost:8080/peperoni-st-rest'
    };

    return config;
} 