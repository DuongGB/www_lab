/*
 * @ {#} LogRepository.java   1.0     10/3/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Log;

import java.util.List;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/3/2024
 * @version:    1.0
 */
public interface LogRepository {
    public List<Log> findAll();

    public Log findById(String id);

    public boolean exists(String id);

    public void save(Log log);

    public Log update(Log log);

    public boolean delete(Log log);

}
