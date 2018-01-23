package com.sapient.dsm.dao;

import com.mongodb.WriteResult;
import com.sapient.dsm.model.ServerInstance;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class ServerInstanceDaoImpl implements ServerInstanceDao {

    private static final String SERVER_INSTANCE_COLLECTION = "ServerInstance";
    private MongoOperations mongoOps;

    public ServerInstanceDaoImpl(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    @Override
    public void create(ServerInstance serverInstance) {
        this.mongoOps.insert(serverInstance, SERVER_INSTANCE_COLLECTION);
    }

    @Override
    public ServerInstance getServerInstance(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoOps.findOne(query, ServerInstance.class, SERVER_INSTANCE_COLLECTION);
    }

    @Override
    public List<ServerInstance> getAllServerInstance() {
        return this.mongoOps.findAll(ServerInstance.class);
    }

    @Override
    public void update(ServerInstance p) {
        this.mongoOps.save(p, SERVER_INSTANCE_COLLECTION);
    }

    @Override
    public int deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        WriteResult result = this.mongoOps.remove(query, ServerInstance.class, SERVER_INSTANCE_COLLECTION);
        return result.getN();
    }

}
