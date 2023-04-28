
param(
    [string]$ProjectDir = ".",
    [string]$Name
)

./java/run-unit-tests.ps1 -RepoName "location-java-test" -ProjectDir $ProjectDir -Name $Name

exit $LASTEXITCODE
