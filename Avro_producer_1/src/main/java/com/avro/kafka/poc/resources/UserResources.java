package com.avro.kafka.poc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import poc.Teacher;

@RestController
@RequestMapping("kafka")
public class UserResources {
	 	@Autowired
	    private KafkaTemplate<String, Teacher> kafkaTemplate;

	    private static final String TOPIC = "Demo-schema-topic";

	    @GetMapping("/publish/{name}")
	    public String post(@PathVariable("name") final String name) {
	    	String infos[] = name.split(":");
	    	String name1 = infos[0];
	    	Long sal = Long.parseLong(infos[2]);
	    	String tech = infos[1];
	
	    	Teacher teacher3 = Teacher.newBuilder().setName(name1).setDept(tech).setSalary(sal).build();
	    	kafkaTemplate.send(TOPIC, "Key1", teacher3);

	        return "Published successfully";
	    }
}