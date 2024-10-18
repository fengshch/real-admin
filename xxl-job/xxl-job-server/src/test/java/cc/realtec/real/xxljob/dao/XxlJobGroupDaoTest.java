package cc.realtec.real.xxljob.dao;

import cc.realtec.real.xxljob.core.model.XxlJobGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class XxlJobGroupDaoTest {

    @Autowired
    private XxlJobGroupDao xxlJobGroupDao;

    @BeforeEach
    void setUp() {
        // Initialize database with test data if necessary
    }

    @Test
    void findAllReturnsListOfXxlJobGroups() {
        List<XxlJobGroup> groups = xxlJobGroupDao.findAll();
        assertNotNull(groups);
        assertFalse(groups.isEmpty());
    }

    @Test
    void findByAddressTypeReturnsListOfXxlJobGroups() {
        int addressType = 1;
        List<XxlJobGroup> groups = xxlJobGroupDao.findByAddressType(addressType);
        assertNotNull(groups);
        assertFalse(groups.isEmpty());
    }

    @Test
    void saveReturnsTrueWhenSuccessful() {
    }

    @Test
    void updateReturnsTrueWhenSuccessful() {
    }

    @Test
    void removeReturnsTrueWhenSuccessful() {
    }

    @Test
    void loadReturnsXxlJobGroup() {
        int id = 1;
        XxlJobGroup group = xxlJobGroupDao.load(id);
        assertNotNull(group);
    }

    @Test
    void pageListReturnsListOfXxlJobGroups() {
        int offset = 0;
        int pageSize = 2;
        String appName = "App";
        String title = "Group";
        List<XxlJobGroup> groups = xxlJobGroupDao.pageList(offset, pageSize, appName, title);
        assertNotNull(groups);
        assertFalse(groups.isEmpty());
    }

    @Test
    void pageListCountReturnsCount() {
        int offset = 3;
        int pageSize = 10;
        String appName = "App";
        String title = "Group";
        long count = xxlJobGroupDao.pageListCount(offset, pageSize, appName, title);
        assertEquals(10, count);
    }
}