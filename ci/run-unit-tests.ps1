
param(
    [string]$ProjectDir = ".",
    [string]$Name,
    [hashtable]$Keys
)

./java/run-unit-tests.ps1 -RepoName "location-java-test" -ProjectDir $ProjectDir -Name $Name -ExtraArgs "-DTestResourceKey=$($Keys.TestResourceKey) -DSuperResourceKey=$($Keys.TestResourceKey)"

exit $LASTEXITCODE
