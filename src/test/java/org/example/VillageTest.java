package org.example;

import org.example.objects.Building;
import org.example.objects.Worker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VillageTest {
    Village village = new Village();

    @BeforeEach
    void newVillage(){
    Village village = new Village();
    }

    @Test
    public void addFarmer() {
        ArrayList<Worker> workers = new ArrayList<>();
        village.setWorkers(workers);
        int expectedSize = 1;
        String expectedName = "John";

        village.AddWorker(expectedName, "farmer");

        Worker worker = workers.get(0);
        assertEquals(expectedName, worker.getName());
        assertEquals(expectedSize, workers.size());
    }
    @Test
    public void addMiner() {
        ArrayList<Worker> workers = new ArrayList<>();
        village.setWorkers(workers);
        int expectedSize = 1;
        String expectedName = "Erik";

        village.AddWorker(expectedName, "miner");

        Worker worker = workers.get(0);
        assertEquals(expectedName, worker.getName());
        assertEquals(expectedSize, workers.size());
    }
    @Test
    public void addBuilder() {
        ArrayList<Worker> workers = new ArrayList<>();
        village.setWorkers(workers);
        int expectedSize = 1;
        String expectedName = "Bengt";

        village.AddWorker(expectedName, "builder");
        Worker worker = workers.get(0);
        assertEquals(expectedName, worker.getName());
        assertEquals(expectedSize, workers.size());
    }
    @Test
    public void addLumberjack() {
        ArrayList<Worker> workers = new ArrayList<>();
        village.setWorkers(workers);
        int expectedSize = 1;
        String expectedName = "Sven";

        village.AddWorker(expectedName, "lumberjack");

        Worker worker = workers.get(0);
        assertEquals(expectedName, worker.getName());
        assertEquals(expectedSize, workers.size());
    }

    @Test
    public void DayForLumberjack_FoodShouldBe9AndWood1(){
        village.AddWorker("David","lumberjack");
        village.Day();
        int expectedFood = 9;
        int expectedWood = 1;
        int expectedDaysGone = 1;

        assertEquals(expectedFood, village.getFood());
        assertEquals(expectedWood, village.getWood());
        assertEquals(expectedDaysGone, village.getDaysGone());
    }
    @Test
    public void DayForMiner_FoodShouldBe9AndMetal1(){
        village.AddWorker("Göran","miner");
        village.Day();
        int expectedFood = 9;
        int expectedMetal = 1;
        int expectedDaysGone = 1;

        assertEquals(expectedFood, village.getFood());
        assertEquals(expectedMetal, village.getMetal());
        assertEquals(expectedDaysGone, village.getDaysGone());
    }
    @Test
    public void DayForFarmer_FoodShouldBe14(){
        village.AddWorker("Tommy","farmer");
        village.Day();
        int expectedFood = 14;
        int expectedDaysGone = 1;


        assertEquals(expectedFood, village.getFood());
        assertEquals(expectedDaysGone, village.getDaysGone());
    }
    @Test
    public void DayForBuilder_FoodShouldBe7AndBuildingFinished(){
        village.AddWorker("Bob","builder");
        village.setWood(100);
        village.setMetal(100);
        village.AddProject("House");
        village.Day();
        village.Day();
        village.Day();
        int expectedDaysGone = 3;
        int expectedFood = 7;
        int expectedTotalBuildings = 4;

        assertEquals(expectedTotalBuildings, village.getBuildings().size());
        assertEquals(expectedFood, village.getFood());
        assertEquals(expectedDaysGone, village.getDaysGone());
    }
    @Test
    public void BuildHouse(){
    village.setWood(10);
    village.setMetal(1);
    village.AddWorker("Bob", "builder");
    village.AddProject("House");
    village.Build("Bob");
    int expectedWood = 5;
    int expectedMetal = 1;

    assertEquals(expectedWood, village.getWood());
    assertEquals(expectedMetal, village.getMetal());

    }
    @Test
    public void BuildWoodmill_ExpectedWood5Metal9(){
        village.setWood(10);
        village.setMetal(10);
        village.AddWorker("Bob", "builder");
        village.AddProject("Woodmill");
        village.Build("Bob");
        int expectedWood = 5;
        int expectedMetal = 9;


        assertEquals(expectedWood, village.getWood());
        assertEquals(expectedMetal, village.getMetal());
    }
    @Test
    public void BuildQuarry_ExpectedWood7Metal5(){
        village.setWood(10);
        village.setMetal(10);
        village.AddWorker("Bob", "builder");
        village.AddProject("Quarry");
        village.Build("Bob");
        int expectedWood = 7;
        int expectedMetal = 5;


        assertEquals(expectedWood, village.getWood());
        assertEquals(expectedMetal, village.getMetal());
    }
    @Test
    public void BuildFarm_ExpectedWood5Metal8(){
        village.setWood(10);
        village.setMetal(10);
        village.AddWorker("Bob", "builder");
        village.AddProject("Farm");
        village.Build("Bob");
        int expectedWood = 5;
        int expectedMetal = 8;

        assertEquals(expectedWood, village.getWood());
        assertEquals(expectedMetal, village.getMetal());
    }
    @Test
    public void BuildCastle_ExpectedWood7Metal5(){
        village.setWood(100);
        village.setMetal(100);
        village.AddWorker("Bob", "builder");
        village.AddProject("Castle");
        village.Build("Bob");
        int expectedWood = 50;
        int expectedMetal = 50;


        assertEquals(expectedWood, village.getWood());
        assertEquals(expectedMetal, village.getMetal());
    }
    @Test
    public void BuildQuarryButNotEnoughMaterial_ExpectedWood100Metal1(){
        village.setWood(100);
        village.setMetal(1);
        village.AddWorker("Bob", "builder");
        village.AddProject("Quarry");
        village.Build("Bob");
        int expectedWood = 100;
        int expectedMetal = 1;


        assertEquals(expectedWood, village.getWood());
        assertEquals(expectedMetal, village.getMetal());
    }
    @Test
    public void AddProject(){
        village.setMetal(100);
        village.setWood(100);
        village.AddProject("House");
        int expectedProjects = 1;
        int expectedWood = 95;
        int expectedMetal = 100;


        assertEquals(expectedProjects, village.getProjects().size());
        assertEquals(expectedWood, village.getWood());
        assertEquals(expectedMetal, village.getMetal());
    }
    @Test
    public void DayWithoutFood_ShouldKillWorker(){
        village.AddWorker("Kalle", "builder");
        village.setFood(1);
        village.Day();
        village.Day();
        village.Day();
        village.Day();
        village.Day();
        village.Day();

        assertTrue(village.isGameOver());


    }

    @Test
    public void WinTheGame(){
        village.AddWorker("Sven", "lumberjack");
        village.AddWorker("jonas", "miner");
        village.AddWorker("Fredrik", "builder");
        village.AddWorker("Bob", "farmer");
        for (int i = 0; i < 6; i++) {
            village.Day();
        }
        village.AddProject("Woodmill");
        for (int i = 0; i < 5; i++) {
            village.Day();
        }
        village.AddProject("Quarry");
        for (int i = 0; i < 30; i++) {
            village.Day();
        }
        village.AddProject("Castle");

        for (int i = 0; i < 50; i++) {
            village.Day();
        }

        int expectedWorkers = 4;
        int expectedBuildings = 6;

        assertEquals(village.getWorkers().size(), expectedWorkers);
        assertEquals(village.getBuildings().size(), expectedBuildings);
        assertTrue(village.isGameOver());



    }
}