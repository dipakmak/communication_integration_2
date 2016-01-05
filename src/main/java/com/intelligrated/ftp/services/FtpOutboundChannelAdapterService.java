
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.intelligrated.ftp.FtpOutboundChannelAdapterSample;

/**
 * TODO(use a phrase to proper describe what the class, it make more user friendly and it is assumed as a high quality code)
 *
 * @author dipak.makwana
 */
@Service
public class FtpOutboundChannelAdapterService {
    
    private static final Logger LOGGER = Logger.getLogger(FtpOutboundChannelAdapterService.class);

    private final File baseFolder = new File("target" + File.separator + "toSend");

    
    ConfigurableApplicationContext ctx =
            new ClassPathXmlApplicationContext("META-INF/spring/integration/FtpOutboundChannelAdapterSample-context.xml");
    

    MessageChannel ftpChannel = ctx.getBean("ftpChannel", MessageChannel.class);
    
    
    public void aciton() throws InterruptedException
    {
        final File fileToSendA;
        final File fileToSendB;
        
        baseFolder.mkdirs();
        
        
        fileToSendA = new File(baseFolder, "a.txt");
        fileToSendB = new File(baseFolder, "b.txt");
        
        final InputStream inputStreamA = FtpOutboundChannelAdapterSample.class.getResourceAsStream("/test-files/a.txt");
        final InputStream inputStreamB = FtpOutboundChannelAdapterSample.class.getResourceAsStream("/test-files/b.txt");
        
        try {
            FileUtils.copyInputStreamToFile(inputStreamA, fileToSendA);
            FileUtils.copyInputStreamToFile(inputStreamB, fileToSendB);
        } catch (IOException e) {
        }

        

        final Message<File> messageA = MessageBuilder.withPayload(fileToSendA).build();
        final Message<File> messageB = MessageBuilder.withPayload(fileToSendB).build();
        
        ftpChannel.send(messageA);
        ftpChannel.send(messageB);

        Thread.sleep(2000);
        LOGGER.info("Successfully transfered file 'a.txt' and 'b.txt' to a remote FTP location.");
        ctx.close();

        
        
    }
    
    @After
    public void cleanup() {
        FileUtils.deleteQuietly(baseFolder);
    }
}

