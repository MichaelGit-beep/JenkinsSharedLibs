def call() {
    def upstream = currentBuild.rawBuild.getCause(hudson.model.Cause$UpstreamCause)
}