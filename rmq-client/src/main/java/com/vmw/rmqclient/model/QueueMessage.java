package com.vmw.rmqclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kunitin
 * Created: 13/12/22
 * Info: Message read from queue
 **/

@Data
@NoArgsConstructor
public class QueueMessage {
    public String message;
    public String senderName;
}
