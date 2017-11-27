package suit;

import org.junit.runners.Suite;

import controller.ReadControllerTest;
import controller.WriteController;
import controller.WriteControllerTest;
import model.GpTest1;
import model.GpTest10;
import model.GpTest11;
import model.GpTest2;
import model.GpTest3;
import model.GpTest4;
import model.GpTest5;
import model.GpTest6;
import model.GpTest7;
import model.GpTest8;
import model.GpTest9;
import model.mapio.DataReaderTest;
import model.mapio.MapValidityTest;
import model.mapio.MapValidityTest1;

import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({ GpTest1.class,
                      GpTest2.class,
                      GpTest3.class,
                      GpTest4.class,
                      GpTest5.class,
                      GpTest6.class,
                      GpTest7.class,
                      GpTest8.class,
                      GpTest9.class,
                      GpTest10.class,
                      GpTest11.class,
                      ReadControllerTest.class,
                      WriteControllerTest.class,
                      DataReaderTest.class,
                      MapValidityTest.class,
                      MapValidityTest1.class
                    })


public class GamePlayTestSuite {
}
