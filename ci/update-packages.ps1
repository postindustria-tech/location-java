
param(
    [string]$ProjectDir = ".",
    [string]$Name
)

./java/run-update-dependencies.ps1 -RepoName "location-java" -ProjectDir $ProjectDir -Name $Name

exit $LASTEXITCODE

