
param(
    [string]$ProjectDir = ".",
    [string]$Name,
    [hashtable]$Keys
)

Write-Host "-DSuperResourceKey=$($Keys.TestResourceKey)"
./java/run-unit-tests.ps1 -RepoName "location-java-test" -ProjectDir $ProjectDir -Name $Name -ExtraArgs "-DSuperResourceKey=$($Keys.TestResourceKey)"

exit $LASTEXITCODE
