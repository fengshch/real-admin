package cc.realtec.real.xxljob.dao;

import cc.realtec.real.xxljob.core.model.XxlJobInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class XxlJobInfoDaoTest {

    @Autowired
    private XxlJobInfoDao xxlJobInfoDao;

    @BeforeEach
    void setUp() {
        // Initialize database with test data if necessary
    }

    @Test
    void pageListReturnsListOfXxlJobInfos() {
        int offset = 0;
        int pageSize = 10;
        int jobGroup = 1;
        int triggerStatus = 1;
        String jobDesc = "Data";
        String executorHandler = "Handler";
        String author = "Ch";
        List<XxlJobInfo> jobs = xxlJobInfoDao.pageList(offset, pageSize, jobGroup, triggerStatus, jobDesc, executorHandler, author);
        assertNotNull(jobs);
        assertFalse(jobs.isEmpty());
    }

    @Test
    void pageListCountReturnsCount() {
        int offset = 0;
        int pageSize = 10;
        int jobGroup = 1;
        int triggerStatus = 1;
        String jobDesc = "Data";
        String executorHandler = "Handler";
        String author = null;
        long count = xxlJobInfoDao.pageListCount(offset, pageSize, jobGroup, triggerStatus, jobDesc, executorHandler, author);
        assertTrue(count > 0);
    }

    @Test
    void saveReturnsTrueWhenSuccessful() {
        XxlJobInfo jobInfo = new XxlJobInfo();
        jobInfo.setJobGroup(1);
        jobInfo.setJobDesc("Test Job");
        jobInfo.setExecutorHandler("Handler");
        jobInfo.setAuthor("Ch");
        jobInfo.setExecutorParam("Param");
        jobInfo.setExecutorRouteStrategy("FIRST");
        jobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        jobInfo.setExecutorTimeout(1000);
        jobInfo.setExecutorFailRetryCount(3);
        jobInfo.setTriggerStatus(1);
        jobInfo.setGlueType("GLUE");
        jobInfo.setTriggerLastTime(System.currentTimeMillis());
        jobInfo.setTriggerNextTime(System.currentTimeMillis());
        int result = xxlJobInfoDao.save(jobInfo);
        assertTrue(result>0);
    }

    @Test
    void loadByIdReturnsXxlJobInfo() {
        int id = 1;
        XxlJobInfo jobInfo = xxlJobInfoDao.loadById(id);
        assertNotNull(jobInfo);
    }

    @Test
    void updateReturnsTrueWhenSuccessful() {
        XxlJobInfo jobInfo = xxlJobInfoDao.loadById(1);
        jobInfo.setJobDesc("Updated Job");
        int result = xxlJobInfoDao.update(jobInfo);
        assertTrue(result>0);
    }

    @Test
    void deleteReturnsTrueWhenSuccessful() {
    }

    @Test
    void getJobsByGroupReturnsListOfXxlJobInfos() {
        int jobGroup = 1;
        List<XxlJobInfo> jobs = xxlJobInfoDao.getJobsByGroup(jobGroup);
        assertNotNull(jobs);
        assertFalse(jobs.isEmpty());
    }

    @Test
    void findAllCountReturnsCount() {
        long count = xxlJobInfoDao.findAllCount();
        assertTrue(count > 0);
    }

    @Test
    void scheduleJobQueryReturnsListOfXxlJobInfos() {
        long maxNextTime = System.currentTimeMillis();
        int pageSize = 10;
        List<XxlJobInfo> jobs = xxlJobInfoDao.scheduleJobQuery(maxNextTime, pageSize);
        assertNotNull(jobs);
        assertFalse(jobs.isEmpty());
    }


    @Test
    void scheduleUpdateReturnTrueWhenSuccessful() {
    }
}