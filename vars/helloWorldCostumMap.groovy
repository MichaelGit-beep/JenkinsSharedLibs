def call(Map config = [:]) {
    sh "echo Hello World ${config.name}, today is ${config.day}"
}