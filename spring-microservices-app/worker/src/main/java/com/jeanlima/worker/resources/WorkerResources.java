package com.jeanlima.worker.resources;

import java.util.List;

import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanlima.worker.entities.Worker;
import com.jeanlima.worker.repository.WorkerRepositotry;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResources {

    private static Logger logger = LoggerFactory.getLogger(WorkerResources.class);

    @Autowired
    private Environment env; //infos sobre o contexto da aplicação

    @Autowired
    private WorkerRepositotry repository;    

    @Value("${test.config}")
    String testConfig;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll(){
        List<Worker> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id){
            
        /* int x = 1;
        if(x == 1) throw new RuntimeException("Teste"); */

        /*
         * Fazendo a requisição esperar 3s e com o timeout padrão do ribbon de 1s,
         * a requisição lança exceção e vai para a rota alterntiva do hystrix
         * timeout pode ser personalizado no client ribbon - no caso payroll que faz as requisições
         */
        /* 
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

        logger.info("PORT = "+env.getProperty("local.server.port"));

        Worker worker = repository.findById(id).get();
        return ResponseEntity.ok(worker);
    }

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs(){
        logger.info("CONFIG= "+testConfig);
        return ResponseEntity.noContent().build();
    }

}
