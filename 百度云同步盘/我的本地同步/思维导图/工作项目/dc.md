# TestHttpServer

#写文件

```json
{
	"type": "FILE",
	"header": {
		"EXTRATYPE": "WRITE"
	},
	"body": {
		"path": "/.dev/47b9ab80559cce18bb3217129278f9f6/10a4bef3c219@00124b0014d0c2f108",
		"cmd": {
			"cmdName": "InfraredRemoteStudyMode",
			"delay": 0,
			"cmdDetails": {
				"deviceType": "TV",
				"controlType": "tv_TurnOn",
				"studyMode": "requestCode"
			}
		},
		"msgId": null
	},
	"timeStamp": 1539762290774,
	"msgId": "38277dbb-b48b-418c-89d1-388d03505b0c"
}
```



# 创建文件  

```json
{
	"type": "FILE",
	"header": {
		"EXTRATYPE": "CREATE"
	},
	"body": {
		"path": "/.dev/47b9ab80559cce18bb3217129278f9f6/10a4bef3c219@00124b0014d0c2f108",
		"fileInfo": {
			"type": "File",
			"operateType": 0,
            "sceneType": null,
			"props": {
				"deviceType": "TV",
				"controlType": "tv_TurnOn",
				"studyMode": "requestCode"
			}
		},
        "msg":{}
		"msgId": null
	},
	"timeStamp": 1539762290774,
	"msgId": "38277dbb-b48b-418c-89d1-388d03505b0c"
}
```

