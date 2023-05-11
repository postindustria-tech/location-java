
param(
    [string]$ProjectDir = ".",
    [string]$Name,
    [hashtable]$Keys
)

./java/run-unit-tests.ps1 -RepoName "location-java" -ProjectDir $ProjectDir -Name $Name -ExtraArgs "-DSuperResourceKey=$($Keys.TestResourceKey)"

exit $LASTEXITCODE
