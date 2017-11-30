package suit;

import model.*;
import org.junit.runners.Suite;

import controller.ReadControllerTest;
import controller.WriteControllerTest;
import model.MaxPlayersTest;
import model.mapio.DataReaderTest;
import model.mapio.MapValidityTest;
import model.mapio.MapValidityTest1;

import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({ players1Test.class,
                      MaxPlayersTest.class,
                      InitialArmiesTest.class,
                      WithoutMapTest.class,
                      UsedAllArmiesB4Play.class,
                      MinReinforceTest.class,
                      IsPlayerKingTest.class,
                      InvalidMovingTest.class,
                      CardExchangeTest.class,
                      AttackTest.class,
                      StrategiesTest.class,
                      SaveProcessTest.class,
                      ReadControllerTest.class,
                      WriteControllerTest.class,
                      DataReaderTest.class,
                      MapValidityTest.class,
                      MapValidityTest1.class
                    })


public class GamePlayTestSuite {
}
