param(
    [Parameter(Mandatory=$true)]
    [string]$RepoName,
    [Parameter(Mandatory=$true)]
    [string]$JavaSDKEnvVar,
    [string]$ProjectDir = "."
)

./java/setup-enviroment.ps1 -RepoName "location-java" -ProjectDir $ProjectDir -JavaSDKEnvVar $JavaSDKEnvVar

exit $LASTEXITCODE
