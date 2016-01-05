
 /**
 * @PROJECT_NAME communication_integration
 * 
 * Copyright (c) 2015 Intelligrated. All rights reserved.
 * 
 * The  information  contained  herein  is  the  confidential  and  proprietary
 * information of Intelligrated.  This information is protected,  among others,
 * by the patent,  copyright,  trademark,  and trade secret laws of  the United
 * States and its several states.  Any use,  copying, or reverse engineering is
 * strictly prohibited. This software has been developed at private expense and
 * accordingly,  if used under Government  contract,  the use,  reproduction or 
 * disclosure  of  this  information  is subject to  the restrictions set forth 
 * under the  contract between  Intelligrated  and its customer.  By viewing or
 * receiving this information, you consent to the foregoing.
 */


package com.intelligrated.ftp.services;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Service;

import com.intelligrated.ftp.FtpInboundChannelAdapterSample;

/**
 * TODO(use a phrase to proper describe what the class, it make more user friendly and it is assumed as a high quality code)
 *
 * @author dipak.makwana
 */
@Service
//@ImportResource("classpath:FtpInboundChannelAdapterService.xml")    
public class FtpInboundChannelAdapterService {
    
    private static final Logger LOGGER = Logger.getLogger(FtpInboundChannelAdapterService.class);
    
    ConfigurableApplicationContext ctx;
    Message<?> message;
    
    public void receiveMessage()
    {
        ctx =
                //new ClassPathXmlApplicationContext("classpath:FtpInboundChannelAdapterService.xml");
                new ClassPathXmlApplicationContext("META-INF/spring/integration/FtpInboundChannelAdapterSample-context.xml");
        PollableChannel ftpChannel = ctx.getBean("ftpChannel", PollableChannel.class);

        Message<?> message1 = ftpChannel.receive(2000);
        Message<?> message2 = ftpChannel.receive(2000);
       // Message<?> message3 = ftpChannel.receive(1000);

        LOGGER.info(String.format("Received first file message: %s.", message1));
        LOGGER.info(String.format("Received first file message: %s.", message1.toString()));
        //LOGGER.info(String.format("Received second file message: %s.", message2));
        //LOGGER.info(String.format("Received nothing else: %s.", message3));

        
        ctx.close();

    }

}

