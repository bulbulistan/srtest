import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitTest {
    
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    swissretest.SwissReTest.update("drop table if exists flight; "
            + "create table flight "
            + "("
            + "id serial, "
            + "flight_id varchar, "
            + "airline_code varchar, "
            + "origin int, "
            + "destination int,"
            + "start_time time, "
            + "arrival_time time,"
            + "plane int,"
            + "primary key (id),"
            + "foreign key (plane) references plane(id),"
            + "foreign key (origin) references airport(id),"
            + "foreign key (destination) references airport(id)"
            + ");"
            + "drop table if exists plane cascade;"
            + "create table plane"
            + "("
            + "id serial, "
            + "registration varchar, "
            + "manufacturer varchar, "
            + "model varchar, "
            + "capacity integer, "
            + "range integer,"
            + "primary key (id)"
            + ");"
            + "drop table if exists airport cascade;"
            + "create table airport"
            + "("
            + "id serial, "
            + "code varchar, "
            + "city varchar, "
            + "country varchar,"
            + "primary key (id)"
            + ")"
        );
            System.out.println("Database updated");    
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {     
    }
    // populate the tables with some data
    
    @After
    public void tearDown() {
    }

    @Test 
    public void Test1() 
    //no direct flights from ZRH to BSL    
    {        
        int expectedResult = 0;
        int actualResult = swissretest.SwissReTest.runquery("select * from "
                + "((flight inner join airport on flight.origin = airport.id) "
                + "inner join airport as airport2 on flight.destination = airport.id)"
                + "where airport.code = 'ZRH' and airport2.code = 'BSL'");
        assertEquals(expectedResult, actualResult);
    }
    
    @Test 
     public void Test2() 
    /*all flights from DXB to ZRH are flown on planes with
    capacity of more than 200 passengers*/
    {
        int expectedResult = 0;
        int actualResult = swissretest.SwissReTest.runquery("select * from "
                + "(((flight inner join airport on flight.origin = airport.id) "
                + "inner join airport as airport2 on flight.destination = airport.id)"
                + "inner join plane on flight.plane = plane.id)"
                + "where airport.code = 'DBX' and airport2.code = 'ZRH' and plane.capacity > 200");
        assertEquals(expectedResult, actualResult);
    }
   }
