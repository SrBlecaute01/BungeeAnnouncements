<h1 align='center'>BungeeAnnouncements</h1>
<p align='center'>
  Send automatic json messages to players on your server.
</p>

## Requiriments

-  Java 8 or later.
-  Bungeecord.

## Download

Clique [here](https://github.com/SrBlecaute01/BungeeAnnouncements/releases/latest) to download the plugin.

## How to use 

-  Download the plugin.
-  Put the file in your server's plugins folder.
-  Start or restart the server.

## How to configure 

The plugin uses json files for messages. The files need to contain the server that 
the message will go to, the interval between messages and the messages themselves.

Configuration example:

```json
{
    "server":"Lobby",
    "interval":30,
    "messages": [

        [
            "",
            {
                "text":"\n"
            },
            {
                "text":"This is a auto announcement!\nClick ",
                "color":"yellow"
            },
            {
                "text":"here",
                "color":"aqua",
                "clickEvent":{
                    "action":"open_url",
                    "value":"https://github.com/SrBlecaute01/BungeeAnnouncements"
                }
            },
            {
                "text":" to go to repository.",
                "color":"yellow"
            },
            {
                "text":"\n "
            }
        ]

    ]
}
```

You can create multiple json files for each server following this template.
To do this, just copy the template, and create a json file with the template 
and place it in the plugin's /servers directory.

To create new posts I like to use this [site](https://minecraft.tools/en/json_text.php). It has a tellraw generator that 
will be used to generate the messages.

![editor](https://imgur.com/DiBPCvr.png)


After editing your message, generate the json, copy and add it to the message list.

![json](https://i.imgur.com/cAzPmar.png)


