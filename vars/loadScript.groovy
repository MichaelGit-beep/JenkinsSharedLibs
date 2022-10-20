def call(String script) {
    def scriptText = libraryResource  "scripts/${script}"
    writeFile file: "./${script}", text: scriptText
    sh "chmod +x ${script}"
}