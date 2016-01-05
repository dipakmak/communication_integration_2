package com.intelligrated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.intelligrated.ftp.services.FtpInboundChannelAdapterService;
import com.intelligrated.ftp.services.FtpOutboundChannelAdapterService;


@SpringBootApplication
public class Application implements CommandLineRunner {

@Autowired
FtpInboundChannelAdapterService  ftpInboundChannelAdapterService;
	
@Autowired
FtpOutboundChannelAdapterService ftpOutboundChannelAdapterService;
	
	/*@Autowired
	MapperService mapperService;*/
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {
		// for now, the only data in data.sql is header data
	
	    ftpInboundChannelAdapterService.receiveMessage();
	    ftpOutboundChannelAdapterService.aciton();
	   
	   
		

		
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
