package com.intelligrated;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.rules.TemporaryFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.samples.ftp.TestSuite;
import org.springframework.integration.samples.ftp.support.TestUserManager;
import org.springframework.integration.test.util.SocketUtils;

import com.intelligrated.ftp.services.FtpInboundChannelAdapterService;
import com.intelligrated.ftp.services.FtpOutboundChannelAdapterService;
import com.intelligrated.ftp.services.FtpOutboundGatewayService;


@SpringBootApplication
public class Application implements CommandLineRunner {

//@Autowired
//FtpInboundChannelAdapterService  ftpInboundChannelAdapterService;
	
@Autowired
FtpOutboundChannelAdapterService ftpOutboundChannelAdapterService;

//@Autowired
//FtpOutboundGatewayService ftpOutboundGatewayService;
    

private static final Logger LOGGER = Logger.getLogger(Application.class);

public static final String FTP_ROOT_DIR       = "target" + File.separator + "ftproot";
public static final String LOCAL_FTP_TEMP_DIR = "target" + File.separator + "local-ftp-temp";
public static final String SERVER_PORT_SYSTEM_PROPERTY = "availableServerPort";

@ClassRule
public static final TemporaryFolder temporaryFolder = new TemporaryFolder();

public static FtpServer server;

@BeforeClass
public static void setupFtpServer() throws FtpException, SocketException, IOException {

    final int availableServerSocket;

    if (System.getProperty(SERVER_PORT_SYSTEM_PROPERTY) == null) {
        availableServerSocket = SocketUtils.findAvailableServerSocket(4444);
        System.setProperty(SERVER_PORT_SYSTEM_PROPERTY, Integer.valueOf(availableServerSocket).toString());
    } else {
        availableServerSocket = Integer.valueOf(System.getProperty(SERVER_PORT_SYSTEM_PROPERTY));
    }

    LOGGER.info("Using open server port..." + availableServerSocket);

    File ftpRoot = new File (FTP_ROOT_DIR);
    ftpRoot.mkdirs();

    TestUserManager userManager = new TestUserManager(ftpRoot.getAbsolutePath());

    FtpServerFactory serverFactory = new FtpServerFactory();
    serverFactory.setUserManager(userManager);
    ListenerFactory factory = new ListenerFactory();

    factory.setPort(availableServerSocket);

    serverFactory.addListener("default", factory.createListener());

    server = serverFactory.createServer();

    server.start();

}

@AfterClass
public static void shutDown() {
    server.stop();
    //FileUtils.deleteQuietly(new File(FTP_ROOT_DIR));
    //FileUtils.deleteQuietly(new File(LOCAL_FTP_TEMP_DIR));
}



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {
		// for now, the only data in data.sql is header data
	
	   //ftpOutboundChannelAdapterService.setupOutboundChannel();
	    //Application.setupFtpServer();
	    
	    ftpOutboundChannelAdapterService.runDemo();;
	    
	  // ftpInboundChannelAdapterService.runDemo(); //Running with external window FTP Service to receive msg.
	   
	   //ftpOutboundGatewayService.testLsGetRm();
	   
	   //Application.shutDown();
	  

//        Thread.sleep(2000);
        
	    
	   
	   
		

		
		// read in 'download' file
//		Stream<String> lines = Files.lines(Paths.get(downloadFilePath));
//		// DOESN"T RETURN ANYTHING lines.forEach(line -> mapperService.map(line));
//		List<String> linesList = lines.filter(line -> !line.equalsIgnoreCase("")).collect(Collectors.toList());
//		
//		lines.close();
//		
//		for (String string : linesList) {
//			System.out.println(string);
//		}
		
		//Stream<String> lines = Files.lines(Paths.get(downloadFilePath));
		
		/**
		 * uses Stream<String> from input file
		 * filters to ignore lines that are empty
		 * maps each line to MapperServices's map()
		 * collects results from map() into Collection which is a List<EPOrderEntity>
		 */
//		List<IEntity> abstractEntityList = lines.filter(line -> !line.equalsIgnoreCase(""))
//				.map(MapperService::map)
//				.collect(Collectors.toList());
		
		// sanity check
		
		
		//lines.close();
	}
    
//    @Bean
//    public CommandLineRunner demo(MapperRepository repository) {
//    	return (args) -> {
    		// the data is pulled from src/main/resources/data.sql
//    		MapperEntity one = new MapperEntity();
//    		one.setId(Long.valueOf(1));
//    		one.setFieldName("sku");
//    		one.setFieldName("SKU");
//    		one.setFieldTableName("header");
//    		one.setIndexStart(1);
//    		one.setIndexLength(5);
//    		one.setDataType("integer");
//    		one.setRecordCode("1");
//    		
//    		repository.save(one);
    		
//    		List<MapperEntity> pojoList = repository.getByRecordCode("1");
//    		
//    		for (MapperEntity pojo : pojoList) {
//				System.out.println(pojo.toString());
//			}
//    	};
//    }
	
	
	
	
	
	
}
