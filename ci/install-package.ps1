
param(
    [string]$ProjectDir = ".",
    [string]$Name
)

./java/install-package.ps1 -RepoName "location-java-test" -ProjectDir $ProjectDir -Name $Name

exit $LASTEXITCODE

