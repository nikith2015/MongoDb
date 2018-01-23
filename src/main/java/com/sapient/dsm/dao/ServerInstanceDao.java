package com.sapient.dsm.dao;

import com.sapient.dsm.model.ServerInstance;

import java.util.List;

public interface ServerInstanceDao {

    List<ServerInstance> getAllServerInstance();

    void create(ServerInstance serverInstance);

    ServerInstance getServerInstance(String id);

    void update(ServerInstance serverInstance);

    int deleteById(String id);
}
