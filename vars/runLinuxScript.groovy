def call(Map config = [:]) {
    def scriptcontents = libraryResources "scripts/${config.name}"
    writeFile file: "${config.name}", text: scriptcontents
    sh """
        chmod +x ./${config.name}
        ./${config.name}
    """
}