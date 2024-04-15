rootProject.name = "Praktikum-Code"
include("Praktikum-01")
include("Praktikum-01:Einmaleins")
findProject(":Praktikum-01:Einmaleins")?.name = "Einmaleins"
include("Praktikum-01:Volumenberechner")
findProject(":Praktikum-01:Volumenberechner")?.name = "Volumenberechner"
include("Praktikum-01:Integrale")
findProject(":Praktikum-01:Integrale")?.name = "Integrale"
include("Praktikum-01:MonteCarloIntegration")
findProject(":Praktikum-01:MonteCarloIntegration")?.name = "MonteCarloIntegration"
