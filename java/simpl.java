
DROP PROCEDURE IF EXISTS sActivityAdd;
CREATE PROCEDURE sActivityAdd(
  IN PEntity_ID INT,
  IN PUser_ID INT,
  IN PExercise_ID INT,
  IN PActivity VARCHAR(1024))
  BEGIN

    DECLARE _id INT;

    INSERT INTO tActivity (Exercise_ID, Activity)
    VALUES (PExercise_ID, PActivity);
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Activity_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sActivityUpdate;
CREATE PROCEDURE sActivityUpdate(IN PEntity_ID   INT, IN PUser_ID INT,IN PActivity_ID INT, IN PExercise_ID INT, IN PActivity VARCHAR(1024))
  BEGIN
    DECLARE _id INT;
    UPDATE tActivity
    SET
    Activity_ID = PActivity_ID, Exercise_ID = PExercise_ID, Activity = PActivity
    WHERE Activity_ID = PActivity_ID;

    SET _id = PActivity_ID;

    CALL sGetTransactionStatus(1,_id, 'Activity_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sActivityDelete;
CREATE PROCEDURE sActivityDelete(IN PEntity_ID INT, IN PUser_ID INT,IN PID INT )
  BEGIN
    UPDATE tActivity
      SET Is_Delete = 1
    WHERE Activity_ID = PID;
    CALL sGetTransactionStatus(1, PID, 'Activity_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sActivityGet;
CREATE PROCEDURE sActivityGet(IN PID INT)
  BEGIN
    SELECT
      Activity_ID,
      Exercise_ID,
      Activity,
      Configuration
    FROM tActivity
    WHERE Activity_ID = PID;
  END;

DROP PROCEDURE IF EXISTS sActivityGetList;
CREATE PROCEDURE sActivityGetList(IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
        Activity_ID,
        Exercise_ID,
        Activity,
        Configuration
      FROM tActivity;
    ELSE
      SELECT
        Activity_ID,
        Exercise_ID,
        Activity,
        Configuration
      FROM tActivity
      WHERE find_in_set(Activity_ID, PIDs);
    END IF;
  END;

DROP PROCEDURE IF EXISTS sActivityObjectGet;
CREATE PROCEDURE sActivityObjectGet(IN PID INT)
  BEGIN
    SELECT
      Activity_ID, Exercise_ID, Activity
    FROM tActivity
    WHERE Activity_ID = PActivity_ID;
  END;



drop PROCEDURE if exists sActivityObjectGetList ;
CREATE PROCEDURE sActivityObjectGetList(IN PExercise_ID INT)
  BEGIN
    SELECT
      Activity_ID,
      Exercise_ID,
      Activity,
      Configuration
    FROM
      tActivity
    WHERE
      CASE WHEN PExercise_ID IS NULL OR PExercise_ID = '' OR PExercise_ID = 0
        THEN
          1 = 1
      ELSE
        Exercise_ID = PExercise_ID
      END
    ORDER BY Activity_ID DESC;

  END;


DROP PROCEDURE IF EXISTS sActivityObjectGetListPage;
CREATE PROCEDURE sActivityObjectGetListPage(IN PActivity_ID int(11), IN PExercise_ID int(11), IN PActivity varchar(1024) , IN PPage_Num  INT, IN PPage_Size INT)
  BEGIN

    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;
    SET _offset = fGetOffset(PPage_Num, PPage_Size);
    SET _total_rec = (SELECT count(*)
                      FROM tActivity
      #       WHERE Status = PStatus

    );
    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
      Activity_ID, Exercise_ID, Activity

      FROM
        tActivity
      #       WHERE Status = PStatus
      ORDER BY Activity_ID DESC
      LIMIT _offset, PPage_Size;

    END IF;
  END;








DROP PROCEDURE IF EXISTS sAllocationPlanHeaderAdd;
CREATE PROCEDURE sAllocationPlanHeaderAdd(
  IN PEntity_ID   INT,
  IN PUser_ID     INT,
  IN PMonth       INT(11),
  IN PYear        YEAR(4),
  IN PCategory_ID INT(11),
  IN PQuantity    FLOAT
)
  BEGIN

    DECLARE _id INT;

    SET _id = (SELECT Plan_ID
               FROM tAllocationPlanHeader
               WHERE Month = PMonth AND Year = PYear AND Category_ID = PCategory_ID
               LIMIT 0, 1);

    IF _id = 0 OR _id IS NULL
    THEN
      INSERT INTO tAllocationPlanHeader (Created_By, Month, Year, Category_ID, Quantity)
      VALUES (PUser_ID, PMonth, PYear, PCategory_ID, PQuantity);
      SET _id = (SELECT last_insert_id());
      CALL sGetTransactionStatus(1, _id, 'AllocationPlanHeader_ID', NULL, NULL);
    ELSE
      CALL sGetTransactionStatus(-1, _id, 'AllocationPlanHeader_ID', 'ERROR001', 'Plan already exists');
    END IF;
  END;


DROP PROCEDURE IF EXISTS sAllocationPlanDetailAdd;
CREATE PROCEDURE sAllocationPlanDetailAdd
  (
    IN PPlan_ID     INT(11),
    IN PProducer_ID INT(11),
    IN PQuantity    FLOAT
  )
  BEGIN

    IF exists(SELECT *
              FROM tAllocationPlanDetail
              WHERE Plan_ID = PPlan_ID AND Producer_ID = PProducer_ID)
    THEN
      UPDATE tAllocationPlanDetail
      SET Quantity = PQuantity
      WHERE Plan_ID = PPlan_ID AND Producer_ID = PProducer_ID;
    ELSE
      INSERT INTO tAllocationPlanDetail (Plan_ID, Producer_ID, Quantity)
      VALUES (PPlan_ID, PProducer_ID, PQuantity);
    END IF;

    CALL sGetTransactionStatus(1, PPlan_ID, 'Plan_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sAllocationPlanHeaderUpdate;
CREATE PROCEDURE sAllocationPlanHeaderUpdate(
  IN PUser_ID     INT, IN PEntity_ID INT,
  IN PPlan_ID     INT(11),
  IN PMonth       INT(11), IN PYear YEAR(4),
  IN PCategory_ID INT(11), IN PQuantity FLOAT)
  BEGIN
    DECLARE _id INT;
    UPDATE tAllocationPlanHeader
    SET
       Created_By = PUser_ID, Month = PMonth, Year = PYear, Category_ID = PCategory_ID,
      Quantity = PQuantity
    WHERE Plan_ID = PPlan_ID;

    SET _id = PPlan_ID;

    CALL sGetTransactionStatus(1, _id, 'AllocationPlanHeader_ID', NULL, NULL);


  END;


DROP PROCEDURE IF EXISTS sAllocationPlanDetailUpdate;
CREATE PROCEDURE sAllocationPlanDetailUpdate(
  IN PPlan_ID INT(11), IN PProducer_ID INT(11), IN PQuantity FLOAT)
  BEGIN
    DECLARE _id INT;
    UPDATE tAllocationPlanDetail
    SET
      Producer_ID = PProducer_ID, Quantity = PQuantity
    WHERE Plan_ID = PPlan_ID AND Producer_ID = PProducer_ID;

    SET _id = PPlan_ID;

    CALL sGetTransactionStatus(1, _id, 'AllocationPlanDetail_ID', NULL, NULL);


  END;

DROP PROCEDURE IF EXISTS sAllocationPlanHeaderDelete;
CREATE PROCEDURE sAllocationPlanHeaderDelete(IN PID INT)
  BEGIN
    DELETE FROM tAllocationPlanHeader
    WHERE Plan_ID = PID;
    CALL sGetTransactionStatus(1, PID, 'AllocationPlanHeader_ID', NULL, NULL);

  END;


DROP PROCEDURE IF EXISTS sAllocationPlanDetailDelete;
CREATE PROCEDURE sAllocationPlanDetailDelete(IN PID INT,IN PProducer_ID INT)
  BEGIN
    DELETE FROM tAllocationPlanDetail
    WHERE Plan_ID = PID AND
    CASE WHEN PProducer_ID = 0 OR PProducer_ID IS NULL
    THEN
    1 = 1
    ELSE
    Producer_ID = PProducer_ID
    END;
    CALL sGetTransactionStatus(1, PID, 'AllocationPlanDetail_ID', NULL, NULL);

  END;



DROP PROCEDURE IF EXISTS sAllocationPlanHeaderGetList;
CREATE PROCEDURE sAllocationPlanHeaderGetList(IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
      Plan_ID, Created_On, Created_By, Month, Year, Category_ID, Quantity

      FROM tAllocationPlanHeader;

    ELSE
      SELECT
      Plan_ID, Created_On, Created_By, Month, Year, Category_ID, Quantity

      FROM tAllocationPlanHeader
      WHERE find_in_set(Plan_ID, PIDs);
    END IF;

  END;



DROP PROCEDURE IF EXISTS sAllocationPlanDetailGetList;
CREATE PROCEDURE sAllocationPlanDetailGetList(IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
      Plan_Detail_ID, Plan_ID, Producer_ID, Quantity

      FROM tAllocationPlanDetail;

    ELSE
      SELECT
      Plan_Detail_ID, Plan_ID, Producer_ID, Quantity

      FROM tAllocationPlanDetail
      WHERE find_in_set(Plan_ID, PIDs);
    END IF;

  END;

DROP PROCEDURE IF EXISTS sAllocationPlanHeaderObjectGetList;
CREATE PROCEDURE sAllocationPlanHeaderObjectGetList(
  IN PUser_ID            INT,
  IN PEntity_ID          INT,
  IN Pyear               INT,
  IN PMonth              INT,
  IN PCategory_ID        INT,
  IN PParent_Category_ID INT

)
  BEGIN


    IF Pyear = 0
    THEN
      SET Pyear = NULL;
    END IF;

    IF PMonth = '' OR PMonth IS NULL
    THEN
      SET PMonth = NULL;
    END IF;

    SELECT
      Plan_ID,
      Created_On,
      Created_By,
      Month,
      Year,
      Category_ID,
      Quantity,
      Status
    FROM
      tAllocationPlanHeader
    WHERE

      Year = ifnull(PYear, Year) AND month = ifnull(PMonth, month) AND
      Category_ID = ifnull(PCategory_ID, Category_ID) AND
      CASE WHEN PParent_Category_ID = 0 OR PParent_Category_ID IS NULL
        THEN
          1 = 1
      ELSE
        Category_ID IN (SELECT Category_ID
                        FROM tCategory
                        WHERE Parent_Category_ID = PParent_Category_ID)
      END
    ORDER BY Plan_ID DESC;
  END;

  DROP PROCEDURE IF EXISTS sAllocationPlanHeaderObjectGetListPage;
CREATE PROCEDURE sAllocationPlanHeaderObjectGetListPage(
  IN PUser_ID            INT,
  IN PEntity_ID          INT,
  IN Pyear               INT,
  IN PMonth              INT,
  IN PCategory_ID        INT,
  IN PParent_Category_ID INT,

  IN PPage_Num           INT,
  IN PPage_Size          INT
)
  BEGIN


    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;


    IF Pyear = 0
    THEN
      SET Pyear = NULL;
    END IF;

    IF PMonth = '' OR PMonth IS NULL
    THEN
      SET PMonth = NULL;
    END IF;


    SET _offset = fGetOffset(PPage_Num, PPage_Size);
    SET _total_rec = (SELECT count(*)
                      FROM tAllocationPlanHeader
                      WHERE

                        Year = ifnull(PYear, Year) AND month = ifnull(PMonth, month) AND
                        Category_ID = ifnull(Category_ID, PCategory_ID) AND
                        CASE WHEN PParent_Category_ID = 0 OR PParent_Category_ID IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Category_ID IN (SELECT Category_ID
                                          FROM tCategory
                                          WHERE Parent_Category_ID = PParent_Category_ID)
                        END

    );
    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        Plan_ID,
        Created_On,
        Created_By,
        Month,
        Year,
        Category_ID,
        Quantity,
        Status
      FROM
        tAllocationPlanHeader
      WHERE

        Year = ifnull(PYear, Year) AND month = ifnull(PMonth, month) AND
        Category_ID = ifnull(PCategory_ID, Category_ID) AND
        CASE WHEN PParent_Category_ID = 0 OR PParent_Category_ID IS NULL
          THEN
            1 = 1
        ELSE
          Category_ID IN (SELECT Category_ID
                          FROM tCategory
                          WHERE Parent_Category_ID = PParent_Category_ID)
        END
      ORDER BY Plan_ID DESC
      LIMIT _offset, PPage_Size;

    END IF;
  END;


DROP PROCEDURE IF EXISTS sAllocationPlanMonthlyGet;
CREATE PROCEDURE sAllocationPlanMonthlyGet
  (
    IN PFY INT
  )
  BEGIN
    IF PFY = 0 OR PFY IS NULL
    THEN SET PFY = (SELECT Financial_Year
                    FROM tFinancialYear
                    WHERE Is_Current = 1
                    LIMIT 0, 1);
    END IF;

    SELECT
      x.Month_Number,
      x.Month_Name,
      x.Month_Short_Name,
      ifnull(y.Planned, 0)  AS Planned,
      ifnull(y.Achieved, 0) AS Achieved
    FROM tMonth x
      LEFT JOIN (
                  SELECT
                    Month,
                    sum(Planned)  AS Planned,
                    sum(Achieved) AS Achieved
                  FROM (
                         SELECT
                           Month,
                           sum(b.Quantity) AS Planned,
                           0               AS Achieved
                         FROM tAllocationPlanHeader a
                           INNER JOIN tAllocationPlanDetail b ON a.Plan_ID = b.Plan_ID
                         WHERE (Year = PFY AND Month >= 4) OR (Year = PFY + 1 AND Month < 4)
                         GROUP BY Month
                         UNION ALL
                         SELECT
                           month(c.Transaction_Date) AS Month,
                           0                         AS Planned,
                           sum(e.Quantity)           AS Achieved
                         FROM tPickupRequest a
                           INNER JOIN tItemHandover b ON a.Cart_ID = b.Cart_ID
                           INNER JOIN tWHGRNHeader c ON b.Handover_ID = c.Handover_ID
                           INNER JOIN tCartItem d ON a.Cart_ID = d.Cart_ID
                           INNER JOIN tCartItemAllocation e ON d.Cart_Item_ID = e.Cart_Item_ID
                         WHERE (year(c.Transaction_Date) = PFY AND month(c.Transaction_Date >= 4)) OR
                               (year(c.Transaction_Date) = PFY + 1 AND month(c.Transaction_Date < 4))
                         GROUP BY Month) m
                  GROUP BY Month) y ON x.Month_Number = y.Month
    ORDER BY x.Sort_Order;
  END;


DROP PROCEDURE IF EXISTS sAllocationPlanGenerate;
CREATE PROCEDURE sAllocationPlanGenerate
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PMonth_Number INT,
    IN PYear         INT,
    IN PCategory_ID  INT
  )
  BEGIN

    DECLARE _done INT DEFAULT 0;

    DECLARE _category_id INT;
    DECLARE _total FLOAT;

    DECLARE _total_cursor CURSOR FOR
      SELECT
        Category_ID,
        sum(Quantity) AS Total
      FROM tCollectionPlan
      WHERE Month = PMonth_Number AND Year = PYear
      GROUP BY Category_ID;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET _done = 1;

    OPEN _total_cursor;
    _total_loop: LOOP
      FETCH _total_cursor
      INTO _category_id, _total;

      IF _done = 1
      THEN
        LEAVE _total_loop;
      END IF;

      IF exists(SELECT *
                FROM tAllocationPlanHeader
                WHERE Month = PMonth_Number AND Year = PYear AND Category_ID = _category_id)
      THEN
        UPDATE tAllocationPlanHeader
        SET Quantity = _total
        WHERE Month = PMonth_Number AND Year = PYear AND Category_ID = _category_id;
      ELSE
        INSERT INTO tAllocationPlanHeader (Created_On, Created_By, Month, Year, Category_ID, Quantity) VALUES
          (now(), PUser_ID, PMonth_Number, PYear, _category_id, _total);
      END IF;

    END LOOP;
    CLOSE _total_cursor;

    CALL sGetTransactionStatus(1, PUser_ID, 'User_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sPendingAllocationGetList;
CREATE PROCEDURE sPendingAllocationGetList
  (
    IN PEntity_ID       INT,
    IN PUser_ID         INT,
    IN PFY              INT,
    IN PIs_Categorywise INT,
    IN PIs_Producerwise INT
  )
  BEGIN
    SELECT
      CASE WHEN PIs_Categorywise = 0
        THEN
          NULL
      ELSE
        Category_ID
      END                                AS Category_ID,
      CASE WHEN PIs_Producerwise = 0
        THEN
          NULL
      ELSE
        Producer_ID
      END                                AS Producer_ID,
      round(ifnull(sum(Target), 0), 2)   AS Target,
      round(ifnull(sum(Achieved), 0), 2) AS Achieved
    FROM (
           SELECT
             c.Category_ID  AS Category_ID,
             a.Producer_ID  AS Producer_ID,
             b.Target_Value AS Target,
             0              AS Achieved
           FROM tProducerTargetHeader a
             INNER JOIN tProducerTargetDetail b ON a.Producer_Target_ID = b.Producer_Target_ID
             INNER JOIN tCategory c ON b.Parameter = c.Category_Code
           WHERE a.Target_Year = PFY
           UNION
           SELECT
             b.Category_ID AS Category_ID,
             c.Producer_ID AS Producer_ID,
             0             AS Target,
             c.Quantity    AS Achieved
           FROM tPickupRequest a
             INNER JOIN tCartItem b ON a.Cart_ID = b.Cart_ID
             INNER JOIN tCartItemAllocation c ON b.Cart_Item_ID = c.Cart_Item_ID
           #            WHERE a.Finacial_Year = PFY
         ) x
    GROUP BY
      CASE WHEN PIs_Categorywise = 1
        THEN Category_ID
      ELSE 1 END,
      CASE
      WHEN PIs_Producerwise = 1
        THEN Producer_ID
      ELSE 1 END
    HAVING Target > 0;
  END;

DROP PROCEDURE IF EXISTS sAppVersionsObjectGetList;
CREATE PROCEDURE sAppVersionsObjectGetList
  (
    IN PCurrent_Version_Number VARCHAR(264),
    IN PPlatform               VARCHAR(64)
  )
  BEGIN
    DECLARE _current_version_release_date DATE;

    SET _current_version_release_date = (SELECT Release_Date
                                         FROM tAppVersions
                                         WHERE Version_Number = PCurrent_Version_Number AND Platform = PPlatform
                                         LIMIT 0, 1);

    SELECT
      _ID,
      Platform,
      Version_ID,
      Version_Number,
      Release_Date,
      Release_Notes,
      Is_Force_Update
    FROM tAppVersions
    WHERE Platform = PPlatform AND
          Release_Date >= _current_version_release_date AND Version_Number <> PCurrent_Version_Number
    ORDER BY Release_Date DESC , `_ID` DESC ;
  END;

DROP PROCEDURE IF EXISTS sAwarnessProgramAdd;
CREATE PROCEDURE sAwarnessProgramAdd
  (
    IN PEntity_ID          INT,
    IN PUser_ID            INT,
    IN PTitle              TEXT,
    IN PDescription        TEXT,
    IN PAwarness_Type      VARCHAR(64),
    IN PURL                TEXT,
    IN PIs_Paid            INT(11),
    IN PIs_POC             INT(11),
    IN PCollected_Waste    FLOAT,
    IN PNum_People_Engaged INT(11),
    IN PNum_People_Reached INT(11),
    IN PProgram_From       VARCHAR(24),
    IN PProgram_To         VARCHAR(24),
    IN PIs_Self            INT(11)
  )
  BEGIN

    DECLARE _id INT;

    INSERT INTO tAwarnessProgram
    (
      Created_By,
      Created_On,
      Title,
      Description,
      Awarness_Type,
      URL,
      Is_Paid,
      Is_POC,
      Collected_Waste,
      Num_People_Engaged,
      Num_People_Reached,
      Program_From,
      Program_To,
      Is_Self,
      Last_Updated_On,
      Last_Updated_By
    )
    VALUES
      (
        PUser_ID,
        now(),
        PTitle,
        PDescription,
        PAwarness_Type,
        PURL,
        PIs_Paid,
        PIs_POC,
        PCollected_Waste,
        PNum_People_Engaged,
        PNum_People_Reached,
        STR_TO_DATE(PProgram_From, '%d/%m/%Y'),
        STR_TO_DATE(PProgram_To, '%d/%m/%Y'),
        PIs_Self,
        now(),
        PUser_ID
      );
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Awarness_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sAwarnessProgramUpdate;
CREATE PROCEDURE sAwarnessProgramUpdate
  (
    IN PEntity_ID          INT,
    IN PUser_ID            INT,
    IN PAwareness_ID       INT,
    IN PTitle              TEXT,
    IN PDescription        TEXT,
    IN PAwarness_Type      VARCHAR(64),
    IN PURL                TEXT,
    IN PIs_Paid            INT(11),
    IN PIs_POC             INT(11),
    IN PCollected_Waste    FLOAT,
    IN PNum_People_Engaged INT(11),
    IN PNum_People_Reached INT(11),
    IN PProgram_From       VARCHAR(24),
    IN PProgram_To         VARCHAR(24),
    IN PIs_Self            INT(11)
  )
  BEGIN

    DECLARE _id INT;

    UPDATE tAwarnessProgram
    SET
      Title              = PTitle,
      Description        = PDescription,
      Awarness_Type      = PAwarness_Type,
      URL                = PURL,
      Is_Paid            = PIs_Paid,
      Is_POC             = PIs_POC,
      Collected_Waste    = PCollected_Waste,
      Num_People_Engaged = PNum_People_Engaged,
      Num_People_Reached = PNum_People_Reached,
      Program_From       = str_to_date(PProgram_From, '%d/%m/%Y'),
      Program_To         = str_to_date(PProgram_To, '%d/%m/%Y'),
      Is_Self            = PIs_Self,
      Last_Updated_On    = now(),
      Last_Updated_By    = PUser_ID
    WHERE Awarness_ID = PAwareness_ID;

    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Awarness_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sAwarnessProgramDelete;
CREATE PROCEDURE sAwarnessProgramDelete
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PAwareness_ID INT
  )
  BEGIN
    DELETE FROM tAwarnessProgram
    WHERE Awarness_ID = PAwareness_ID;
    CALL sGetTransactionStatus(1, PAwareness_ID, 'Awareness_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sAwarnessProgramEntitiesAdd;
CREATE PROCEDURE sAwarnessProgramEntitiesAdd
  (
    IN PEntity_ID           INT,
    IN PUser_ID             INT,
    IN PAwarness_ID         INT(11),
    IN PAwareness_Entity_ID INT(11),
    IN PEntity_Name         VARCHAR(1024)
  )
  BEGIN

    DECLARE _id INT;

    INSERT INTO tAwarnessProgramEntities
    (
      Awarness_ID, Entity_ID, Entity_Name
    )
    VALUES
      (
        PAwarness_ID, PAwareness_Entity_ID, PEntity_Name
      );
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'AwarnessProgramEntities_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sAwarnessProgramEntitiesDelete;
CREATE PROCEDURE sAwarnessProgramEntitiesDelete
  (
    IN PEntity_ID   INT,
    IN PUser_ID     INT,
    IN PAwarness_ID INT(11)
  )
  BEGIN
    DELETE FROM tAwarnessProgramEntities
    WHERE Awarness_ID = PAwarness_ID;

    CALL sGetTransactionStatus(1, PAwarness_ID, 'Awareness_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sAwarnessProgramPartnersAdd;
CREATE PROCEDURE sAwarnessProgramPartnersAdd
  (
    IN PEntity_ID   INT,
    IN PUser_ID     INT,
    IN PAwarness_ID INT(11),
    IN PPartner_ID  INT(11)
  )
  BEGIN

    DECLARE _id INT;

    INSERT INTO tAwarnessProgramPartners (Awarness_ID, Partner_ID)
    VALUES (PAwarness_ID, PPartner_ID);
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Awareness_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sAwarnessProgramPartnersDelete;
CREATE PROCEDURE sAwarnessProgramPartnersDelete
  (
    IN PEntity_ID   INT,
    IN PUser_ID     INT,
    IN PAwarness_ID INT(11)
  )
  BEGIN

    DELETE FROM tAwarnessProgramPartners
    WHERE Awarness_ID = PAwarness_ID;

    CALL sGetTransactionStatus(1, PAwarness_ID, 'Awareness_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sAwarnessProgramReachAdd;
CREATE PROCEDURE sAwarnessProgramReachAdd
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PAwarness_ID  INT(11),
    IN PState_ID     INT(11),
    IN PCity_ID      INT(11),
    IN PPlace_Detail TEXT
  )
  BEGIN

    DECLARE _id INT;

    INSERT INTO tAwarnessProgramReach (Awarness_ID, State_ID, City_ID, Place_Detail)
    VALUES (PAwarness_ID, PState_ID, PCity_ID, PPlace_Detail);
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Awareness_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sAwarnessProgramReachDelete;
CREATE PROCEDURE sAwarnessProgramReachDelete
  (
    IN PEntity_ID   INT,
    IN PUser_ID     INT,
    IN PAwarness_ID INT(11)
  )
  BEGIN

    DELETE FROM tAwarnessProgramReach
    WHERE Awarness_ID = PAwarness_ID;

    CALL sGetTransactionStatus(1, PAwarness_ID, 'Awareness_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sAwarnessProgramObjectGetListPage;
CREATE PROCEDURE sAwarnessProgramObjectGetListPage(IN PEntity_ID          INT(11),
                                                   IN PUser_ID            INT,
                                                   IN PAwarness_Type      VARCHAR(64),
                                                   IN PIs_Paid            INT(11),
                                                   IN PIs_POC             INT(11),
                                                   IN PProgram_From       VARCHAR(24),
                                                   IN PProgram_To         VARCHAR(24),
                                                   IN PIs_Self            INT(11),
                                                   IN PState_ID           INT,
                                                   IN PCity_ID            INT,
                                                   IN PHierarchy_Type     VARCHAR(64),
                                                   IN PSearch_Key         VARCHAR(32),
                                                   IN PEntity_Search_Key  VARCHAR(32),
                                                   IN PPartner_Search_Key VARCHAR(32),
                                                   IN PPage_Num           INT,
                                                   IN PPage_Size          INT
)
  BEGIN
    DECLARE _program_from DATE;
    DECLARE _program_to DATE;
    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;

    IF PProgram_From IS NULL OR PProgram_From = ''
    THEN
      SET _program_from = NULL;
    ELSE
      SET _program_from = STR_TO_DATE(PProgram_From, '%d/%m/%Y');
    END IF;

    IF PProgram_To IS NULL OR PProgram_To = ''
    THEN
      SET _program_to = NULL;
    ELSE
      SET _program_to = STR_TO_DATE(PProgram_To, '%d/%m/%Y');
    END IF;

    if PAwarness_Type = '' then set PAwarness_Type = null; end if ;


    SET _offset = fGetOffset(PPage_Num, PPage_Size);

    SET _total_rec = (SELECT count(*)
                      FROM
                        tAwarnessProgram
                      WHERE

                        CASE WHEN PState_ID IS NOT NULL AND PState_ID <> 0
                          THEN
                            Awarness_ID IN (SELECT Awarness_ID
                                            FROM tAwarnessProgramReach
                                            WHERE State_ID = PState_ID)
                        ELSE
                          1 = 1
                        END AND
                        CASE WHEN PCity_ID IS NOT NULL AND PCity_ID <> 0
                          THEN
                            Awarness_ID IN (SELECT Awarness_ID
                                            FROM tAwarnessProgramReach
                                            WHERE City_ID = PCity_ID)
                        ELSE
                          1 = 1
                        END AND

                        CASE WHEN PEntity_Search_Key IS NOT NULL AND PEntity_Search_Key <> ''
                          THEN
                            Awarness_ID IN (SELECT x.Awarness_ID
                                            FROM tAwarnessProgramEntities x LEFT JOIN tEntity y
                                                ON x.Entity_ID = y.Entity_ID
                                            WHERE x.Entity_Name LIKE concat('%', PEntity_Search_Key, '%') OR
                                                  y.Entity_Name LIKE concat('%', PEntity_Search_Key, '%'))
                        ELSE
                          1 = 1
                        END AND

                        CASE WHEN PPartner_Search_Key IS NOT NULL AND PPartner_Search_Key <> ''
                          THEN
                            Awarness_ID IN (SELECT x.Awarness_ID
                                            FROM tAwarnessProgramPartners x LEFT JOIN tEntity y
                                                ON x.Partner_ID = y.Entity_ID
                                            WHERE
                                              y.Entity_Name LIKE concat('%', PEntity_Search_Key, '%'))
                        ELSE
                          1 = 1
                        END AND

                        Awarness_Type = ifnull(PAwarness_Type, Awarness_Type)
                        AND
                        Is_Self = ifnull(PIs_Self, Is_Self)
                        AND
                        CASE WHEN PProgram_From = '' OR PProgram_From IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Program_From >= ifnull(_program_from, Program_From)
                        END
                        AND
                        CASE WHEN PProgram_To = '' OR PProgram_To IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Program_To <= ifnull(_program_to, Program_To)
                        END
                        AND
                        CASE WHEN PSearch_Key IS NULL OR PSearch_Key = ''
                          THEN 1 = 1
                        ELSE
                          Description LIKE concat('%', PSearch_Key, '%')
                          OR
                          Title LIKE concat('%', PSearch_Key, '%')
                        END
                        AND
                        Is_Paid = ifnull(PIs_Paid, Is_Paid)
                        AND
                        Is_POC = ifnull(PIs_POC, Is_POC)

    );
    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        Awarness_ID,
        Created_By,
        Created_On,
        Title,
        Description,
        Awarness_Type,
        URL,
        Is_Paid,
        Is_POC,
        Collected_Waste,
        Num_People_Engaged,
        Num_People_Reached,
        date_format(Program_From, '%d/%m/%Y') AS Program_From,
        date_format(Program_To, '%d/%m/%Y')   AS Program_To,
        Is_Self
      FROM
        tAwarnessProgram
      WHERE

        CASE WHEN PState_ID IS NOT NULL AND PState_ID <> 0
          THEN
            Awarness_ID IN (SELECT Awarness_ID
                            FROM tAwarnessProgramReach
                            WHERE State_ID = PState_ID)
        ELSE
          1 = 1
        END AND
        CASE WHEN PCity_ID IS NOT NULL AND PCity_ID <> 0
          THEN
            Awarness_ID IN (SELECT Awarness_ID
                            FROM tAwarnessProgramReach
                            WHERE City_ID = PCity_ID)
        ELSE
          1 = 1
        END AND

        CASE WHEN PEntity_Search_Key IS NOT NULL AND PEntity_Search_Key <> ''
          THEN
            Awarness_ID IN (SELECT x.Awarness_ID
                            FROM tAwarnessProgramEntities x LEFT JOIN tEntity y
                                ON x.Entity_ID = y.Entity_ID
                            WHERE x.Entity_Name LIKE concat('%', PEntity_Search_Key, '%') OR
                                  y.Entity_Name LIKE concat('%', PEntity_Search_Key, '%'))
        ELSE
          1 = 1
        END AND

        CASE WHEN PPartner_Search_Key IS NOT NULL AND PPartner_Search_Key <> ''
          THEN
            Awarness_ID IN (SELECT x.Awarness_ID
                            FROM tAwarnessProgramPartners x LEFT JOIN tEntity y
                                ON x.Partner_ID = y.Entity_ID
                            WHERE
                              y.Entity_Name LIKE concat('%', PEntity_Search_Key, '%'))
        ELSE
          1 = 1
        END AND

        Awarness_Type = ifnull(PAwarness_Type, Awarness_Type)
        AND
        Is_Self = ifnull(PIs_Self, Is_Self)
        AND
        CASE WHEN PProgram_From = '' OR PProgram_From IS NULL
          THEN
            1 = 1
        ELSE
          Program_From >= ifnull(_program_from, Program_From)
        END
        AND
        CASE WHEN PProgram_To = '' OR PProgram_To IS NULL
          THEN
            1 = 1
        ELSE
          Program_To <= ifnull(_program_to, Program_To)
        END
        AND
        CASE WHEN PSearch_Key IS NULL OR PSearch_Key = ''
          THEN 1 = 1
        ELSE
          Description LIKE concat('%', PSearch_Key, '%')
          OR
          Title LIKE concat('%', PSearch_Key, '%')
        END
        AND
        Is_Paid = ifnull(PIs_Paid, Is_Paid)
        AND
        Is_POC = ifnull(PIs_POC, Is_POC)
      ORDER BY Awarness_ID DESC
      LIMIT _offset, PPage_Size;


    END IF;
  END;

  DROP PROCEDURE IF EXISTS sAwarnessProgramEntitiesObjectGetList;
CREATE PROCEDURE sAwarnessProgramEntitiesObjectGetList(IN PAwareness_ID INT)
  BEGIN
    SELECT
      Awarness_ID,
      Entity_ID,
      Entity_Name
    FROM tAwarnessProgramEntities
    WHERE Awarness_ID = PAwareness_ID;
  END;

DROP PROCEDURE IF EXISTS sAwarnessProgramPartnersObjectGetList;
CREATE PROCEDURE sAwarnessProgramPartnersObjectGetList(IN PAwareness_ID INT)
  BEGIN
    SELECT
      Awarness_ID,
      Partner_ID
    FROM tAwarnessProgramPartners
    WHERE Awarness_ID = PAwareness_ID;
  END;

DROP PROCEDURE IF EXISTS sAwarnessProgramReachObjectGetList;
CREATE PROCEDURE sAwarnessProgramReachObjectGetList(IN PAwareness_ID INT)
  BEGIN
    SELECT
      Awarness_ID,
      State_ID,
      City_ID,
      Place_Detail
    FROM tAwarnessProgramReach
    WHERE Awarness_ID = PAwareness_ID;
  END;



drop procedure if exists sAwarenessCalendarGet ;
CREATE PROCEDURE sAwarenessCalendarGet(IN PEntity_ID INT, IN PUser_ID INT, IN PMonth INT, IN PYear INT)
  BEGIN
    SELECT
      Awarness_ID,
      Created_By,
      Created_On,
      Title,
      Description,
      Awarness_Type,
      URL,
      Is_Paid,
      Is_POC,
      Collected_Waste,
      Num_People_Engaged,
      Num_People_Reached,
      date_format(Program_From,'%d/%m/%Y') as Program_From,
      date_format(Program_To,'%d/%m/%Y') as Program_To,
      Is_Self
    FROM
      tAwarnessProgram
    WHERE
      CASE WHEN PMonth = 0 OR PMonth IS NULL
        THEN
          1 = 1
      ELSE
        month(Program_From) = PMonth OR month(Program_To) = PMonth
      END AND
      CASE WHEN PYear = 0 OR PYear IS NULL
        THEN
          1 = 1
      ELSE
        year(Program_From) = PYear OR year(Program_To) = PMonth
      END;
  END;


DROP PROCEDURE IF EXISTS sBankAdd;
CREATE PROCEDURE sBankAdd(
  IN PEntiy_ID INT,
  IN PUser_ID INT,
  IN PBank_Name VARCHAR(24))
  BEGIN
    DECLARE _id INT;
    INSERT INTO tBank (Bank_Name)
      VALUE (PBank_Name);
    SET _id = LAST_INSERT_ID();
    CALL sGetTransactionStatus(1, _id, "Bank_ID", NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sBankUpdate;
CREATE PROCEDURE sBankUpdate(
  IN PEntiy_ID  INT,
  IN PUser_ID   INT,
  IN PBank_ID   INT,
  IN PBank_Name VARCHAR(264))
  BEGIN
    UPDATE tBank
    SET
      Bank_ID   = PBank_ID,
      Bank_Name = PBank_Name

    WHERE Bank_ID = PBank_ID;
    CALL sGetTransactionStatus(1, PBank_ID, "Bank_ID", NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBankDelete;
CREATE PROCEDURE sBankDelete(
  IN PEntiy_ID INT,
  IN PUser_ID INT,
  IN PBank_ID INT)
  BEGIN
    UPDATE tBank
    SET Is_Delete = 1
    WHERE Bank_ID = PBank_ID;
    CALL sGetTransactionStatus(1, PBank_ID, "Bank_ID", NULL, NULL);

  END;


DROP PROCEDURE IF EXISTS sBankGet;
CREATE PROCEDURE sBankGet(IN PBank_ID INT)
  BEGIN
    SELECT
      Bank_ID,
      Bank_Name
    FROM tBank
    WHERE
      Bank_ID = PBank_ID;

  END;


DROP PROCEDURE IF EXISTS sBankGetList;
CREATE PROCEDURE sBankGetList(IN PBank_IDs TEXT)
  BEGIN
    IF PBank_IDs = '' OR PBank_IDs IS NULL
    THEN
      SELECT
        Bank_ID,
        Bank_Name
      FROM tBank
      ORDER BY Bank_Name;
    ELSE
      SELECT
        Bank_ID,
        Bank_Name
      FROM tBank
      WHERE FIND_IN_SET(Bank_ID, PBank_IDs)
      ORDER BY Bank_Name;
    END IF;


  END;


DROP PROCEDURE IF EXISTS sBillDraftHeaderAdd;
CREATE PROCEDURE sBillDraftHeaderAdd
  (
    IN PEntity_ID             INT,
    IN PUser_ID               INT,
    IN PBill_Entity_ID        INT,
    IN PTransaction_ID        INT,
    IN PObject_Type           VARCHAR(264),
    IN PDescription           TEXT CHARACTER SET utf8
                              COLLATE utf8_unicode_ci,
    IN POther_Price           FLOAT,
    IN PTransportation_Number VARCHAR(2019)
  )
  BEGIN
    DECLARE _id INT;

    INSERT INTO tBillDraftHeader (Created_On, Created_By, Entity_ID, Transaction_ID, Object_Type, Last_Updated_On, Last_Updated_By, Description, Other_Price, Transportation_Number)
    VALUES
      (now(), PUser_ID, PBill_Entity_ID, PTransaction_ID, PObject_Type, now(), PUser_ID, PDescription, POther_Price,
       PTransportation_Number);

    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Draft_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDraftHeaderUpdate;
CREATE PROCEDURE sBillDraftHeaderUpdate(IN PEntity_ID      INT, IN PUser_ID INT, IN PDraft_ID INT,
                                             IN PBill_Entity_ID INT, IN PTransaction_ID INT,
                                             IN PObject_Type    VARCHAR(256), IN PDescription TEXT,
                                             IN POther_Price    FLOAT, IN PTransportation_Number VARCHAR(1024))
  BEGIN

    UPDATE tBillDraftHeader
    SET Entity_ID           = PBill_Entity_ID,
      Transaction_ID        = PTransaction_ID,
      Object_Type           = PObject_Type,
      Description           = PDescription,
      Other_Price           = POther_Price,
      Last_Updated_By       = PUser_ID,
      Last_Updated_On       = now(),
      Transportation_Number = PTransportation_Number
    WHERE Draft_ID = PDraft_ID;

    CALL sGetTransactionStatus(1, PDraft_ID, 'Draft_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDraftHeaderDelete;
CREATE PROCEDURE sBillDraftHeaderDelete
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PDraft_ID  INT
  )
  BEGIN
    DELETE FROM tBillDraftHeader
    WHERE Draft_ID = PDraft_ID;

    CALL sGetTransactionStatus(1, PDraft_ID, 'Draft_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDraftDetailAdd;
CREATE PROCEDURE sBillDraftDetailAdd
  (
    IN PDraft_ID   INT,
    IN PJobType_ID INT,
    IN PQuantity   FLOAT,
    IN PUOM_ID     INT,
    IN PPrice      FLOAT,
    IN PGST        FLOAT
  )
  BEGIN
    INSERT INTO tBillDraftDetail (Draft_ID, JobType_ID, Quantity, UOM_ID, Price, GST) VALUES
      (PDraft_ID, PJobType_ID, PQuantity, PUOM_ID, PPrice, PGST);

    CALL sGetTransactionStatus(1, PDraft_ID, 'Draft_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDraftDetailUpdate;
CREATE PROCEDURE sBillDraftDetailUpdate
  (
    IN PDraft_ID   INT,
    IN PJobType_ID INT,
    IN PQuantity   FLOAT,
    IN PUOM_ID     INT,
    IN PPrice      FLOAT,
    IN PGST        FLOAT
  )
  BEGIN
    UPDATE tBillDraftDetail
    SET
      Quantity = PQuantity,
      UOM_ID   = PUOM_ID,
      Price    = PPrice,
      GST      = PGST
    WHERE Draft_ID = PDraft_ID AND JobType_ID = PJobType_ID;

    CALL sGetTransactionStatus(1, PDraft_ID, 'Draft_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDraftDetailDelete;
CREATE PROCEDURE sBillDraftDetailDelete
  (
    IN PDraft_ID   INT,
    IN PJobType_ID INT
  )
  BEGIN
    IF PJobType_ID = 0 OR PJobType_ID IS NULL
    THEN
      DELETE FROM tBillDraftDetail
      WHERE Draft_ID = PDraft_ID;
    ELSE
      DELETE FROM tBillDraftDetail
      WHERE Draft_ID = PDraft_ID AND JobType_ID = PJobType_ID;
    END IF;
    CALL sGetTransactionStatus(1, PDraft_ID, 'Draft_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDraftHeaderTotalsUpdate;
CREATE PROCEDURE sBillDraftHeaderTotalsUpdate
  (
    IN PDraft_ID INT
  )
  BEGIN
    DECLARE _total_base_amount FLOAT DEFAULT 0;
    DECLARE _total_gst_amount FLOAT DEFAULT 0;

    SET _total_base_amount = (SELECT sum(Price)
                              FROM tBillDraftDetail
                              WHERE Draft_ID = PDraft_ID);
    SET _total_gst_amount = (SELECT sum(((Price * GST) / 100))
                             FROM tBillDraftDetail
                             WHERE Draft_ID = PDraft_ID);

    UPDATE tBillDraftHeader
    SET Total_Base_Amount = _total_base_amount, Total_GST_Amount = _total_gst_amount
    WHERE Draft_ID = PDraft_ID;

    CALL sGetTransactionStatus(1, PDraft_ID, 'Draft_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDraftHeaderGet;
CREATE PROCEDURE sBillDraftHeaderGet
  (
    IN PID INT
  )
  BEGIN
    SELECT
      Draft_ID,
      Created_On,
      Created_By,
      Entity_ID,
      Transaction_ID,
      Object_Type,
      Description,
      Other_Price,
      Total_Base_Amount,
      Total_GST_Amount,
      Transportation_Number,
      Last_Updated_On,
      Last_Updated_By
    FROM tBillDraftHeader
    WHERE Draft_ID = PID;
  END;

DROP PROCEDURE IF EXISTS sBillDraftHeaderObjectGet;
CREATE PROCEDURE sBillDraftHeaderObjectGet
  (
    IN PTransaction_ID INT,
    IN PObject_Type    VARCHAR(264)
  )
  BEGIN
    SELECT
      Draft_ID,
      Created_On,
      Created_By,
      Entity_ID,
      Transaction_ID,
      Object_Type,
      Description,
      Other_Price,
      Total_Base_Amount,
      Total_GST_Amount,
      Transportation_Number,
      Last_Updated_On,
      Last_Updated_By
    FROM tBillDraftHeader
    WHERE Transaction_ID = PTransaction_ID AND Object_Type = PObject_Type;
  END;


DROP PROCEDURE IF EXISTS sBillDraftDetailGetList;
CREATE PROCEDURE sBillDraftDetailGetList
  (
    IN PDraft_ID INT
  )
  BEGIN
    SELECT
      Draft_ID,
      JobType_ID,
      Quantity,
      UOM_ID,
      Price,
      GST
    FROM tBillDraftDetail
    WHERE Draft_ID = PDraft_ID;
  END;

DROP PROCEDURE IF EXISTS sIsTransactionBilled;
CREATE PROCEDURE sIsTransactionBilled
  (
    IN PTransaction_ID INT,
    IN PObject_Type    VARCHAR(64)
  )
  BEGIN
    IF exists(SELECT *
              FROM tBillDetail
              WHERE Transaction_ID = PTransaction_ID AND Object_Type = PObject_Type)
    THEN
      SELECT 1 AS billed
      FROM dual;
    ELSE
      SELECT 0 AS billed
      FROM dual;
    END IF;
  END;

/* Seller Bill */
DROP PROCEDURE IF EXISTS sSellerBillForLSPBillingGetListPage;
CREATE PROCEDURE sSellerBillForLSPBillingGetListPage
  (
    IN PEntity_ID              INT,
    IN PUser_ID                INT,
    IN PFY                     INT,
    IN PFrom_Date              VARCHAR(24),
    IN PTo_Date                VARCHAR(24),
    IN PLSP_ID                 INT,
    IN PFrom_State             INT,
    IN PTo_State               INT,
    IN PIs_Pending             INT,
    IN PSeller_Bill_Search_Key VARCHAR(64),
    IN PLSP_Name_Search_Key    VARCHAR(64),
    IN PSeller_Name_Search_Key VARCHAR(64),
    IN PPage_Num               INT,
    IN PPage_Size              INT
  )
  BEGIN
    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;
    DECLARE _from_date DATE DEFAULT NULL;
    DECLARE _to_date DATE DEFAULT NULL;

    IF PFrom_Date = '' OR PFrom_Date IS NULL
    THEN
      SET _from_date = NULL;
    ELSE
      SET _from_date = STR_TO_DATE(PFrom_Date, '%d/%m/%Y');
    END IF;

    IF PTo_Date = '' OR PTo_Date IS NULL
    THEN
      SET _to_date = NULL;
    ELSE
      SET _to_date = STR_TO_DATE(PTo_Date, '%d/%m/%Y');
    END IF;

    IF PIs_Pending IS NULL
    THEN SET PIs_Pending = 1; END IF;

    IF PFY = 0 OR PFY IS NULL
    THEN SET PFY = fGetFinacialYearByDate(curdate()); END IF;

    SET _offset = fGetOffset(PPage_Num, PPage_Size);
    SET _total_rec = (SELECT count(*)
                      FROM tSellerBill a
                        INNER JOIN tItemHandover b ON a.Cart_ID = b.Cart_ID
                        INNER JOIN tWHGRNHeader c ON b.Handover_ID = c.Handover_ID
                        INNER JOIN tCart d ON a.Cart_ID = d.Cart_ID
                        INNER JOIN tPickupRequest e ON a.Cart_ID = e.Cart_ID
                        INNER JOIN tEntity f ON d.Entity_ID = f.Entity_ID
                        INNER JOIN tEntity g ON e.Logistic_Partner_Entity_ID = g.Entity_ID
                      WHERE
                        e.Is_Direct <> 2 AND
                        CASE WHEN PIs_Pending = 1
                          THEN
                            a.Bill_ID NOT IN (SELECT Transaction_ID
                                              FROM tBillDraftHeader
                                              WHERE Object_Type = 'SELLERINV')
                        ELSE
                          a.Bill_ID IN (SELECT Transaction_ID
                                        FROM tBillDraftHeader
                                        WHERE Object_Type = 'SELLERINV')
                        END AND
                        fGetFinacialYearByDate(date(a.Bill_Date)) = PFY AND
                        e.Logistic_Partner_Entity_ID = ifnull(PLSP_ID, e.Logistic_Partner_Entity_ID) AND
                        CASE WHEN _from_date = 0 OR _from_date IS NULL
                          THEN
                            1 = 1
                        ELSE
                          date(a.Bill_Date) >= _from_date
                        END AND
                        CASE WHEN _to_date = 0 OR _to_date IS NULL
                          THEN
                            1 = 1
                        ELSE
                          date(a.Bill_Date) <= _to_date
                        END AND
                        CASE WHEN PSeller_Bill_Search_Key = '' OR PSeller_Bill_Search_Key IS NULL
                          THEN
                            1 = 1
                        ELSE
                          a.Updated_Bill_Number LIKE concat('%', PSeller_Bill_Search_Key, '%')
                        END AND
                        CASE WHEN PLSP_Name_Search_Key = '' OR PLSP_Name_Search_Key IS NULL
                          THEN
                            1 = 1
                        ELSE
                          g.Entity_Name LIKE concat('%', PLSP_Name_Search_Key, '%')
                        END AND
                        CASE WHEN PSeller_Name_Search_Key = '' OR PSeller_Name_Search_Key IS NULL
                          THEN
                            1 = 1
                        ELSE
                          f.Entity_Name LIKE concat('%', PSeller_Name_Search_Key, '%')
                        END);

    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        a.Bill_ID,
        date_format(a.Bill_Date, '%d/%m/%Y') AS Bill_Date,
        a.Bill_Number,
        a.Updated_Bill_Number,
        a.Cart_ID,
        a.Status,
        a.Bill_Date                          AS oBill_Date
      FROM tSellerBill a
        INNER JOIN tItemHandover b ON a.Cart_ID = b.Cart_ID
        INNER JOIN tWHGRNHeader c ON b.Handover_ID = c.Handover_ID
        INNER JOIN tCart d ON a.Cart_ID = d.Cart_ID
        INNER JOIN tPickupRequest e ON a.Cart_ID = e.Cart_ID
        INNER JOIN tEntity f ON d.Entity_ID = f.Entity_ID
        INNER JOIN tEntity g ON e.Logistic_Partner_Entity_ID = g.Entity_ID
      WHERE
        e.Is_Direct <> 2 AND
        CASE WHEN PIs_Pending = 1
          THEN
            a.Bill_ID NOT IN (SELECT Transaction_ID
                              FROM tBillDraftHeader
                              WHERE Object_Type = 'SELLERINV')
        ELSE
          a.Bill_ID IN (SELECT Transaction_ID
                        FROM tBillDraftHeader
                        WHERE Object_Type = 'SELLERINV')
        END AND
        fGetFinacialYearByDate(date(a.Bill_Date)) = PFY AND
        e.Logistic_Partner_Entity_ID = ifnull(PLSP_ID, e.Logistic_Partner_Entity_ID) AND
        CASE WHEN _from_date = 0 OR _from_date IS NULL
          THEN
            1 = 1
        ELSE
          date(a.Bill_Date) >= _from_date
        END AND
        CASE WHEN _to_date = 0 OR _to_date IS NULL
          THEN
            1 = 1
        ELSE
          date(a.Bill_Date) <= _to_date
        END AND
        CASE WHEN PSeller_Bill_Search_Key = '' OR PSeller_Bill_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          a.Updated_Bill_Number LIKE concat('%', PSeller_Bill_Search_Key, '%')
        END AND
        CASE WHEN PLSP_Name_Search_Key = '' OR PLSP_Name_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          g.Entity_Name LIKE concat('%', PLSP_Name_Search_Key, '%')
        END AND
        CASE WHEN PSeller_Name_Search_Key = '' OR PSeller_Name_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          f.Entity_Name LIKE concat('%', PSeller_Name_Search_Key, '%')
        END
      ORDER BY oBill_Date DESC
      LIMIT _offset, PPage_Size;
    END IF;
  END;


DROP PROCEDURE IF EXISTS sKaroInvoiceForLSPBillingGetListPage;
CREATE PROCEDURE sKaroInvoiceForLSPBillingGetListPage
  (
    IN PEntity_ID           INT,
    IN PUser_ID             INT,
    IN PFY                  INT,
    IN PFrom_Date           VARCHAR(24),
    IN PTo_Date             VARCHAR(24),
    IN PLSP_ID              INT,
    IN PFrom_State          INT,
    IN PTo_State            INT,
    IN PIs_Pending          INT,
    IN PIs_Direct           INT,
    IN PKaro_Inv_Search_Key VARCHAR(64),
    IN PLSP_Name_Search_Key VARCHAR(64),
    IN PPage_Num            INT,
    IN PPage_Size           INT
  )
  BEGIN
    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;
    DECLARE _from_date DATE DEFAULT NULL;
    DECLARE _to_date DATE DEFAULT NULL;

    IF PFrom_Date = '' OR PFrom_Date IS NULL
    THEN
      SET _from_date = NULL;
    ELSE
      SET _from_date = STR_TO_DATE(PFrom_Date, '%d/%m/%Y');
    END IF;

    IF PTo_Date = '' OR PTo_Date IS NULL
    THEN
      SET _to_date = NULL;
    ELSE
      SET _to_date = STR_TO_DATE(PTo_Date, '%d/%m/%Y');
    END IF;

    IF PIs_Pending IS NULL
    THEN SET PIs_Pending = 1; END IF;

    IF PFY = 0 OR PFY IS NULL
    THEN SET PFY = fGetFinacialYearByDate(curdate()); END IF;

    SET _offset = fGetOffset(PPage_Num, PPage_Size);
    SET _total_rec = (SELECT count(*)
                      FROM tInvoiceHeader a
                        INNER JOIN tDispatch b ON a.Dispatch_ID = b.Dispatch_ID
                        INNER JOIN tEntity c ON b.Logistic_ID = c.Entity_ID
                        INNER JOIN tEntity d ON a.Recycler_ID = d.Entity_ID
                        INNER JOIN tWarehouse e ON a.WH_ID = e.WH_ID
                      WHERE
                        CASE WHEN PIs_Direct IS NULL
                          THEN
                            1 = 1
                        ELSE
                          CASE WHEN PIs_Direct = 1
                            THEN
                              a.WH_ID = 5
                          ELSE
                            a.WH_ID <> 5
                          END
                        END AND
                        CASE WHEN PIs_Pending = 1
                          THEN
                            a.Invoice_ID NOT IN (SELECT Transaction_ID
                                                 FROM tBillDraftHeader
                                                 WHERE Object_Type = 'KAROINV')
                        ELSE
                          a.Invoice_ID IN (SELECT Transaction_ID
                                           FROM tBillDraftHeader
                                           WHERE Object_Type = 'KAROINV')
                        END AND
                        fGetFinacialYearByDate(date(a.Invoice_Date)) = PFY AND
                        CASE WHEN PFrom_State = 0 OR PFrom_State IS NULL
                          THEN
                            1 = 1
                        ELSE
                          e.State_ID = PFrom_State
                        END AND
                        CASE WHEN PTo_State = 0 OR PTo_State IS NULL
                          THEN
                            1 = 1
                        ELSE
                          d.State_ID = PTo_State
                        END AND
                        b.Logistic_ID = ifnull(PLSP_ID, b.Logistic_ID) AND
                        CASE WHEN _from_date = 0 OR _from_date IS NULL
                          THEN
                            1 = 1
                        ELSE
                          date(a.Invoice_Date) >= _from_date
                        END AND
                        CASE WHEN _to_date = 0 OR _to_date IS NULL
                          THEN
                            1 = 1
                        ELSE
                          date(a.Invoice_Date) <= _to_date
                        END AND
                        CASE WHEN PKaro_Inv_Search_Key = '' OR PKaro_Inv_Search_Key IS NULL
                          THEN
                            1 = 1
                        ELSE
                          a.Updated_Invoice_Number LIKE concat('%', PKaro_Inv_Search_Key, '%')
                        END AND
                        CASE WHEN PLSP_Name_Search_Key = '' OR PLSP_Name_Search_Key IS NULL
                          THEN
                            1 = 1
                        ELSE
                          c.Entity_Name LIKE concat('%', PLSP_Name_Search_Key, '%')
                        END);

    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        a.Invoice_ID,
        date_format(Invoice_Date, '%d/%m/%Y') AS Invoice_Date,
        a.Created_By,
        Invoice_Number,
        Recycler_ID,
        Invoice_Base_Amount,
        Invoice_GST_Amount,
        Invoice_Total_Amount,
        a.Dispatch_ID,
        a.Status,
        a.WH_ID,
        Updated_Invoice_Number,
        a.Last_Updated_On,
        a.Last_Updated_By,
        Transaction_Date,
        Invoice_Date                          AS OInvoice_Date
      FROM tInvoiceHeader a
        INNER JOIN tDispatch b ON a.Dispatch_ID = b.Dispatch_ID
        INNER JOIN tEntity c ON b.Logistic_ID = c.Entity_ID
        INNER JOIN tEntity d ON a.Recycler_ID = d.Entity_ID
        INNER JOIN tWarehouse e ON a.WH_ID = e.WH_ID
      WHERE
        CASE WHEN PIs_Direct IS NULL
          THEN
            1 = 1
        ELSE
          CASE WHEN PIs_Direct = 1
            THEN
              a.WH_ID = 5
          ELSE
            a.WH_ID <> 5
          END
        END AND
        CASE WHEN PIs_Pending = 1
          THEN
            a.Invoice_ID NOT IN (SELECT Transaction_ID
                                 FROM tBillDraftHeader
                                 WHERE Object_Type = 'KAROINV')
        ELSE
          a.Invoice_ID IN (SELECT Transaction_ID
                           FROM tBillDraftHeader
                           WHERE Object_Type = 'KAROINV')
        END AND
        fGetFinacialYearByDate(date(a.Invoice_Date)) = PFY AND
        CASE WHEN PFrom_State = 0 OR PFrom_State IS NULL
          THEN
            1 = 1
        ELSE
          e.State_ID = PFrom_State
        END AND
        CASE WHEN PTo_State = 0 OR PTo_State IS NULL
          THEN
            1 = 1
        ELSE
          d.State_ID = PTo_State
        END AND
        b.Logistic_ID = ifnull(PLSP_ID, b.Logistic_ID) AND
        CASE WHEN _from_date = 0 OR _from_date IS NULL
          THEN
            1 = 1
        ELSE
          date(a.Invoice_Date) >= _from_date
        END AND
        CASE WHEN _to_date = 0 OR _to_date IS NULL
          THEN
            1 = 1
        ELSE
          date(a.Invoice_Date) <= _to_date
        END AND
        CASE WHEN PKaro_Inv_Search_Key = '' OR PKaro_Inv_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          a.Updated_Invoice_Number LIKE concat('%', PKaro_Inv_Search_Key, '%')
        END AND
        CASE WHEN PLSP_Name_Search_Key = '' OR PLSP_Name_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          c.Entity_Name LIKE concat('%', PLSP_Name_Search_Key, '%')
        END
      ORDER BY OInvoice_Date DESC
      LIMIT _offset, PPage_Size;
    END IF;
  END;


DROP PROCEDURE IF EXISTS sAllInvoicesObjectGetListPage;
CREATE PROCEDURE sAllInvoicesObjectGetListPage
  (
    IN PEntity_ID   INT,
    IN PUser_ID     INT,
    IN PFY          YEAR,
    IN PFrom_Date   VARCHAR(24),
    IN PTo_Date     VARCHAR(24),
    IN PObject_Type VARCHAR(264),
    IN PPage_Num    INT,
    IN PPage_Size   INT
  )
  BEGIN
    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;
    DECLARE _from_date DATE;
    DECLARE _to_date DATE;

    SET _offset = fGetOffset(PPage_Num, PPage_Size);

    IF PFY = 0 OR PFY IS NULL
    THEN
      SET PFY = fGetFinacialYearByDate(curdate());
    END IF;

    IF PFrom_Date = '' OR PFrom_Date IS NULL
    THEN
      SET _from_date = NULL;
    ELSE
      SET _from_date = str_to_date(PFrom_Date, '%d/%m/%Y');
    END IF;

    IF PTo_Date = '' OR PTo_Date IS NULL
    THEN
      SET _to_date = NULL;
    ELSE
      SET _to_date = str_to_date(PTo_Date, '%d/%m/%Y');
    END IF;

    SET _total_rec = (
      SELECT count(*)
      FROM (
             SELECT
               a.Bill_ID   AS Invoice_ID,
               'SELLERINV' AS Object_Type,
               a.Bill_Date AS dt
             FROM
               tSellerBill a
               INNER JOIN tPickupRequest b ON a.Cart_ID = b.Cart_ID
             WHERE
               b.Is_Direct <> 2 AND
               b.Logistic_Partner_Entity_ID = PEntity_ID
               AND
               CASE WHEN _from_date IS NULL
                 THEN
                   1 = 1
               ELSE
                 date(a.Bill_Date) >= _from_date
               END AND
               CASE WHEN _to_date IS NULL
                 THEN
                   1 = 1
               ELSE
                 date(a.Bill_Date) <= _to_date
               END AND
               a.Bill_ID NOT IN (SELECT DISTINCT y.Transaction_ID
                                 FROM tBillHeader x INNER JOIN tBillDetail y ON x.Bill_ID = y.Bill_ID
                                 WHERE x.Entity_ID = PEntity_ID AND y.Object_Type = 'SELLERINV')
             UNION ALL
             SELECT
               a.Invoice_ID   AS Invoice_ID,
               'KAROINV'      AS Object_Type,
               a.Invoice_Date AS dt
             FROM tInvoiceHeader a
               INNER JOIN tDispatch b ON a.Dispatch_ID = b.Dispatch_ID AND
                                         CASE WHEN _from_date IS NULL
                                           THEN
                                             1 = 1
                                         ELSE
                                           date(a.Invoice_Date) >= _from_date
                                         END AND
                                         CASE WHEN _to_date IS NULL
                                           THEN
                                             1 = 1
                                         ELSE
                                           date(a.Invoice_Date) <= _to_date
                                         END
             WHERE b.Logistic_ID = PEntity_ID
                   AND
                   a.Invoice_ID NOT IN (SELECT DISTINCT y.Transaction_ID
                                        FROM tBillHeader x INNER JOIN tBillDetail y ON x.Bill_ID = y.Bill_ID
                                        WHERE x.Entity_ID = PEntity_ID AND y.Object_Type = 'KAROINV')
           ) x
    );

    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);

    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        x.Invoice_ID,
        x.Object_Type
      FROM (
             SELECT
               a.Bill_ID   AS Invoice_ID,
               'SELLERINV' AS Object_Type,
               a.Bill_Date AS dt
             FROM
               tSellerBill a
               INNER JOIN tPickupRequest b ON a.Cart_ID = b.Cart_ID
             WHERE
               b.Is_Direct <> 2 AND
               b.Logistic_Partner_Entity_ID = PEntity_ID
               AND
               CASE WHEN _from_date IS NULL
                 THEN
                   1 = 1
               ELSE
                 date(a.Bill_Date) >= _from_date
               END AND
               CASE WHEN _to_date IS NULL
                 THEN
                   1 = 1
               ELSE
                 date(a.Bill_Date) <= _to_date
               END AND
               a.Bill_ID NOT IN (SELECT DISTINCT y.Transaction_ID
                                 FROM tBillHeader x INNER JOIN tBillDetail y ON x.Bill_ID = y.Bill_ID
                                 WHERE x.Entity_ID = PEntity_ID AND y.Object_Type = 'SELLERINV')
             UNION ALL
             SELECT
               a.Invoice_ID   AS Invoice_ID,
               'KAROINV'      AS Object_Type,
               a.Invoice_Date AS dt
             FROM tInvoiceHeader a
               INNER JOIN tDispatch b ON a.Dispatch_ID = b.Dispatch_ID AND
                                         CASE WHEN _from_date IS NULL
                                           THEN
                                             1 = 1
                                         ELSE
                                           date(a.Invoice_Date) >= _from_date
                                         END AND
                                         CASE WHEN _to_date IS NULL
                                           THEN
                                             1 = 1
                                         ELSE
                                           date(a.Invoice_Date) <= _to_date
                                         END
             WHERE b.Logistic_ID = PEntity_ID
                   AND
                   a.Invoice_ID NOT IN (SELECT DISTINCT y.Transaction_ID
                                        FROM tBillHeader x INNER JOIN tBillDetail y ON x.Bill_ID = y.Bill_ID
                                        WHERE x.Entity_ID = PEntity_ID AND y.Object_Type = 'KAROINV')
           ) x
      ORDER BY x.dt DESC
      LIMIT _offset, PPage_Size;
    END IF;
  END;

DROP PROCEDURE IF EXISTS sBillHeaderAdd;
CREATE PROCEDURE sBillHeaderAdd
  (
    IN PEntity_ID      INT,
    IN PUser_ID        INT,
    IN PBill_Entity_ID INT,
    IN PBill_Type      VARCHAR(64),
    IN PBill_Frequency VARCHAR(64),
    IN PFrom_Date      VARCHAR(24),
    IN PTo_Date        VARCHAR(24),
    IN PBill_Date      VARCHAR(24),
    IN PBill_Number    VARCHAR(1024),
    IN PDescription    TEXT CHARACTER SET utf8
                       COLLATE utf8_unicode_ci,
    IN POther_Price    FLOAT
  )
  BEGIN
    DECLARE _id INT;

    INSERT INTO tBillHeader (Created_On, Created_By, Entity_ID, Bill_Type, Bill_Frequency, From_Date, To_Date, Bill_Date, Bill_Number, Description, Other_Price, Last_Updated_On, Last_Updated_By)
    VALUES
      (now(), PUser_ID, PBill_Entity_ID, PBill_Type, PBill_Frequency, str_to_date(PFrom_Date, '%d/%m/%Y'),
              str_to_date(PTo_Date, '%d/%m/%Y'), str_to_date(PBill_Date, '%d/%m/%Y'), PBill_Number,
              PDescription, POther_Price, now(), PUser_ID);

    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Bill_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillHeaderUpdate;
CREATE PROCEDURE sBillHeaderUpdate
  (
    IN PEntity_ID      INT,
    IN PUser_ID        INT,
    IN PBill_ID        INT,
    IN PBill_Type      VARCHAR(64),
    IN PBill_Frequency VARCHAR(64),
    IN PFrom_Date      VARCHAR(24),
    IN PTo_Date        VARCHAR(24),
    IN PBill_Date      VARCHAR(24),
    IN PBill_Number    VARCHAR(1024),
    IN PDescription    TEXT CHARACTER SET utf8
                       COLLATE utf8_unicode_ci,
    IN POther_Price    FLOAT
  )
  BEGIN
    UPDATE tBillHeader
    SET Bill_Type    = PBill_Type,
      Bill_Frequency = PBill_Frequency,
      From_Date      = str_to_date(PFrom_Date, '%d/%m/%Y'),
      To_Date        = str_to_date(PTo_Date, '%d/%m/%Y'),
      Bill_Date      = str_to_date(PBill_Date, '%d/%m/%Y'),
      Bill_Number    = PBill_Number,
      Description    = PDescription,
      Other_Price    = POther_Price
    WHERE Bill_ID = PBill_ID;

    CALL sGetTransactionStatus(1, PBill_ID, 'Bill_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sBillHeaderDelete;
CREATE PROCEDURE sBillHeaderDelete
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PBill_ID   INT
  )
  BEGIN
    DELETE FROM tBillHeader
    WHERE Bill_ID = PBill_ID;
    CALL sGetTransactionStatus(1, PBill_ID, 'Bill_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillHeaderApprove;
CREATE PROCEDURE sBillHeaderApprove
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PBill_ID   INT
  )
  BEGIN
    UPDATE tBillHeader
    SET Approved_By = PUser_ID
    WHERE Bill_ID = PBill_ID;

    CALL sGetTransactionStatus(1, PBill_ID, 'Bill_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillHeaderTotalsUpdate;
CREATE PROCEDURE sBillHeaderTotalsUpdate
  (
    IN PBill_ID INT
  )
  BEGIN
    DECLARE _total_base_amount FLOAT DEFAULT 0;
    DECLARE _total_gst_amount FLOAT DEFAULT 0;
    DECLARE _other_cost FLOAT DEFAULT 0;
    DECLARE _other_cost_gst FLOAT DEFAULT 0;
    DECLARE _gst FLOAT DEFAULT 0;

    SET _total_base_amount = (SELECT sum((Quantity * Price))
                              FROM tBillDetailPrice a
                                INNER JOIN tBillDetail b ON a.Bill_Detail_ID = b.Bill_Detail_ID
                              WHERE b.Bill_ID = PBill_ID);
    SET _total_gst_amount = (SELECT sum(((Quantity * Price * GST) / 100))
                             FROM tBillDetailPrice a
                               INNER JOIN tBillDetail b ON a.Bill_Detail_ID = b.Bill_Detail_ID
                             WHERE b.Bill_ID = PBill_ID);

    SET _other_cost = (SELECT Other_Price
                       FROM tBillHeader
                       WHERE Bill_ID = PBill_ID
                       LIMIT 0, 1);


    IF _other_cost <= 0 OR _other_cost IS NULL
    THEN
      UPDATE tBillHeader
      SET Total_Base_Amount = _total_base_amount, Total_GST_Amount = _total_gst_amount
      WHERE Bill_ID = PBill_ID;
    ELSE
      SET _gst = (SELECT a.GST
                  FROM tBillDetailPrice a INNER JOIN tBillDetail b ON a.Bill_Detail_ID = b.Bill_Detail_ID
                  WHERE b.Bill_ID = PBill_ID
                  LIMIT 0, 1);
      SET _other_cost_gst = (_other_cost * _gst) / 100;
      UPDATE tBillHeader
      SET
        Total_Base_Amount = _total_base_amount + _other_cost,
        Total_GST_Amount  = _total_gst_amount + _other_cost_gst
      WHERE Bill_ID = PBill_ID;
    END IF;

    CALL sGetTransactionStatus(1, PBill_ID, 'Bill_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillHeaderGet;
CREATE PROCEDURE sBillHeaderGet
  (
    IN PID INT
  )
  BEGIN
    SELECT
      Bill_ID,
      Created_On,
      Created_By,
      Entity_ID,
      Bill_Type,
      Bill_Frequency,
      date_format(From_Date, '%d/%m/%Y') AS From_Date,
      date_format(To_Date, '%d/%m/%Y')   AS To_Date,
      date_format(Bill_Date, '%d/%m/%Y') AS Bill_Date,
      Bill_Number,
      Description,
      Other_Price,
      Total_Base_Amount,
      Total_GST_Amount,
      Total_Payable_Base_Amount,
      Total_Payable_GST_Amount,
      Total_Payable_GST_Prcernt,
      Total_Payable_Amount,
      Final_Remarks,
      Payment_Status,
      Approved_By,
      Status,
      Last_Updated_On,
      Last_Updated_By
    FROM tBillHeader
    WHERE Bill_ID = PID
    LIMIT 0, 1;
  END;

DROP PROCEDURE IF EXISTS sBillHeaderObjectGetListPage;
CREATE PROCEDURE sBillHeaderObjectGetListPage
  (
    IN PEntity_ID              INT,
    IN PUser_ID                INT,
    IN PBill_Entity_ID         INT,
    IN PFY                     INT,
    IN PFrom_Date              VARCHAR(24),
    IN PTo_Date                VARCHAR(24),
    IN PBill_Type              VARCHAR(64),
    IN PBill_Frequency         VARCHAR(64),
    IN PApproved_By            INT,
    IN PPayment_Status         VARCHAR(64),
    IN PStatus                 VARCHAR(64),
    IN PBill_Number_Search_Key VARCHAR(64),
    IN PEntity_Name_Search_Key VARCHAR(64),
    IN PPage_Num               INT,
    IN PPage_Size              INT
  )
  BEGIN

    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;
    DECLARE _from_date DATE;
    DECLARE _to_date DATE;

    SET _offset = fGetOffset(PPage_Num, PPage_Size);

    IF PFY = 0 OR PFY IS NULL
    THEN
      SET PFY = fGetFinacialYearByDate(curdate());
    END IF;

    IF PFrom_Date = '' OR PFrom_Date IS NULL
    THEN
      SET _from_date = NULL;
    ELSE
      SET _from_date = str_to_date(PFrom_Date, '%d/%m/%Y');
    END IF;

    IF PTo_Date = '' OR PTo_Date IS NULL
    THEN
      SET _to_date = NULL;
    ELSE
      SET _to_date = str_to_date(PTo_Date, '%d/%m/%Y');
    END IF;

    IF PBill_Type = ''
    THEN SET PBill_Type = NULL; END IF;
    IF PBill_Frequency = ''
    THEN SET PBill_Frequency = NULL; END IF;
    IF PPayment_Status = ''
    THEN SET PPayment_Status = NULL; END IF;
    IF PApproved_By = 0
    THEN SET PApproved_By = NULL; END IF;

    SET _total_rec = (SELECT count(*)
                      FROM tBillHeader
                      WHERE
                        Entity_ID = ifnull(PBill_Entity_ID, Entity_ID) AND
                        fGetFinacialYearByDate(date(Bill_Date)) = PFY AND
                        CASE WHEN PStatus = '' OR PStatus IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Status = PStatus
                        END
                        AND
                        CASE WHEN _from_date = '' OR _from_date IS NULL
                          THEN
                            1 = 1
                        ELSE
                          date(Bill_Date) >= _from_date
                        END AND
                        CASE WHEN _to_date = '' OR _to_date IS NULL
                          THEN
                            1 = 1
                        ELSE
                          date(Bill_Date) <= _to_date
                        END AND
                        CASE WHEN PBill_Type = '' OR PBill_Type IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Bill_Type = PBill_Type
                        END
                        AND

                        CASE WHEN PBill_Frequency = '' OR PBill_Frequency IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Bill_Frequency = PBill_Frequency
                        END
                        AND

                        CASE WHEN PPayment_Status = '' OR PPayment_Status IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Payment_Status = PPayment_Status
                        END
                        AND
                        CASE WHEN PBill_Number_Search_Key = '' OR PBill_Number_Search_Key IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Bill_Number LIKE concat('%', PBill_Number_Search_Key, '%')
                        END AND
                        CASE WHEN PEntity_Name_Search_Key = '' OR PEntity_Name_Search_Key IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Entity_ID IN (SELECT Entity_ID
                                        FROM tEntity
                                        WHERE Entity_Name LIKE concat('%', PEntity_Name_Search_Key, '%'))
                        END);

    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);

    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        Bill_ID,
        Created_On,
        Created_By,
        Entity_ID,
        Bill_Type,
        Bill_Frequency,
        date_format(From_Date, '%d/%m/%Y') AS From_Date,
        date_format(To_Date, '%d/%m/%Y')   AS To_Date,
        date_format(Bill_Date, '%d/%m/%Y') AS Bill_Date,
        Bill_Number,
        Description,
        Other_Price,
        Total_Base_Amount,
        Total_GST_Amount,
        Total_Payable_Base_Amount,
        Total_Payable_GST_Amount,
        Total_Payable_GST_Prcernt,
        Total_Payable_Amount,
        Payment_Status,
        Approved_By,
        Status,
        Last_Updated_On,
        Last_Updated_By,
        Bill_Date                          AS oBill_Date
      FROM tBillHeader
      WHERE
        Entity_ID = ifnull(PBill_Entity_ID, Entity_ID) AND
        fGetFinacialYearByDate(date(Bill_Date)) = PFY AND
        CASE WHEN PStatus = '' OR PStatus IS NULL
          THEN
            1 = 1
        ELSE
          Status = PStatus
        END
        AND
        CASE WHEN _from_date = '' OR _from_date IS NULL
          THEN
            1 = 1
        ELSE
          DATE(Bill_Date) >= _from_date
        END AND
        CASE WHEN _to_date = '' OR _to_date IS NULL
          THEN
            1 = 1
        ELSE
          DATE(Bill_Date) <= _to_date
        END AND
        CASE WHEN PBill_Type = '' OR PBill_Type IS NULL
          THEN
            1 = 1
        ELSE
          Bill_Type = PBill_Type
        END
        AND

        CASE WHEN PBill_Frequency = '' OR PBill_Frequency IS NULL
          THEN
            1 = 1
        ELSE
          Bill_Frequency = PBill_Frequency
        END
        AND

        CASE WHEN PPayment_Status = '' OR PPayment_Status IS NULL
          THEN
            1 = 1
        ELSE
          Payment_Status = PPayment_Status
        END
        AND
        CASE WHEN PBill_Number_Search_Key = '' OR PBill_Number_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          Bill_Number LIKE concat('%', PBill_Number_Search_Key, '%')
        END AND
        CASE WHEN PEntity_Name_Search_Key = '' OR PEntity_Name_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          Entity_ID IN (SELECT Entity_ID
                        FROM tEntity
                        WHERE Entity_Name LIKE concat('%', PEntity_Name_Search_Key, '%'))
        END
      ORDER BY oBill_Date DESC
      LIMIT _offset, PPage_Size;

    END IF;

  END;

/* Detail */
DROP PROCEDURE IF EXISTS sBillDetailAdd;
CREATE PROCEDURE sBillDetailAdd
  (
    IN PBill_ID               INT,
    IN PTransaction_ID        INT,
    IN PObject_Type           VARCHAR(264),
    IN PTransportation_Number VARCHAR(1024)
  )
  BEGIN
    DECLARE _id INT;
    INSERT INTO tBillDetail (Bill_ID, Transaction_ID, Object_Type, Transportation_Number) VALUES
      (PBill_ID, PTransaction_ID, PObject_Type, PTransportation_Number);

    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Bill_Detail_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sBillDetailUpdate;
CREATE PROCEDURE sBillDetailUpdate
  (
    IN PBill_Detail_ID INT,
    IN PTransaction_ID INT,
    IN PObject_Type    VARCHAR(264),
    IN PTransportation_Number VARCHAR(1024)
  )
  BEGIN
    UPDATE tBillDetail
    SET Transaction_ID = PTransaction_ID, Object_Type = PObject_Type, Transportation_Number = PTransportation_Number
    WHERE Bill_Detail_ID = PBill_Detail_ID;
    CALL sGetTransactionStatus(1, PBill_Detail_ID, 'Bill_Detail_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDetailDelete;
CREATE PROCEDURE sBillDetailDelete
  (
    IN PBill_ID        INT,
    IN PBill_Detail_ID INT
  )
  BEGIN
    DELETE FROM tBillDetail
    WHERE Bill_ID = PBill_ID AND
          CASE WHEN PBill_Detail_ID = 0 OR PBill_Detail_ID IS NULL
            THEN
              1 = 1
          ELSE
            Bill_Detail_ID = PBill_Detail_ID
          END;

    CALL sGetTransactionStatus(1, PBill_Detail_ID, 'Bill_Detail_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDetailGetList;
CREATE PROCEDURE sBillDetailGetList
  (
    IN PBill_ID INT
  )
  BEGIN
    SELECT
      Bill_Detail_ID,
      Bill_ID,
      Transaction_ID,
      Object_Type,
      Transportation_Number
    FROM tBillDetail
    WHERE Bill_ID = PBill_ID;
  END;


/* Detail price */
DROP PROCEDURE IF EXISTS sBillDetailPriceAdd;
CREATE PROCEDURE sBillDetailPriceAdd
  (
    IN PBill_Detail_ID INT,
    IN PJobType_ID     INT,
    IN PQuantity       FLOAT,
    IN PUOM_ID         INT,
    IN PPrice          FLOAT,
    IN PGST            FLOAT
  )
  BEGIN
    INSERT INTO tBillDetailPrice (Bill_Detail_ID, JobType_ID, Quantity, UOM_ID, Price, GST) VALUES
      (PBill_Detail_ID, PJobType_ID, PQuantity, PUOM_ID, PPrice, PGST);

    CALL sGetTransactionStatus(1, PBill_Detail_ID, 'Bill_Detail_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sBillDetailPriceUpdate;
CREATE PROCEDURE sBillDetailPriceUpdate
  (
    IN PBill_Detail_ID INT,
    IN PJobType_ID     INT,
    IN PQuantity       FLOAT,
    IN PUOM_ID         INT,
    IN PPrice          FLOAT,
    IN PGST            FLOAT
  )
  BEGIN
    UPDATE tBillDetailPrice
    SET Quantity = PQuantity,
      UOM_ID     = PUOM_ID,
      Price      = PPrice,
      GST        = PGST
    WHERE Bill_Detail_ID = PBill_Detail_ID AND JobType_ID = PJobType_ID;

    CALL sGetTransactionStatus(1, PBill_Detail_ID, 'Bill_Detail_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillDetailPriceDelete;
CREATE PROCEDURE sBillDetailPriceDelete
  (
    IN PBill_Detail_ID INT,
    IN PJobtype_ID     INT,
    IN PBill_ID        INT
  )
  BEGIN

    IF PBill_ID <> 0 AND PBill_ID IS NOT NULL
    THEN
      DELETE FROM tBillDetailPrice
      WHERE Bill_Detail_ID IN (SELECT x.Bill_Detail_ID
                               FROM tBillDetail x
                               WHERE Bill_ID = PBill_ID);
    ELSE
      DELETE FROM tBillDetailPrice
      WHERE Bill_Detail_ID = PBill_Detail_ID AND
            CASE WHEN PJobtype_ID = 0 OR PJobtype_ID IS NULL
              THEN 1 = 1
            ELSE JobType_ID = PJobtype_ID END;
    END IF;

    CALL sGetTransactionStatus(1, PBill_Detail_ID, 'Bill_Detail_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sBillDetailPriceGetList;
CREATE PROCEDURE sBillDetailPriceGetList
  (
    IN PBill_Detail_ID INT
  )
  BEGIN
    SELECT
      Bill_Detail_ID,
      JobType_ID,
      Quantity,
      UOM_ID,
      Price,
      GST
    FROM tBillDetailPrice
    WHERE Bill_Detail_ID = PBill_Detail_ID;
  END;

/* Reconcile */
DROP PROCEDURE IF EXISTS sBillReconcile;
CREATE PROCEDURE sBillReconcile
  (
    IN PEntity_ID      INT,
    IN PUser_ID        INT,
    IN PBill_ID        INT,
    IN PTransaction_ID INT,
    IN PObject_Type    VARCHAR(64),
    IN PJobType_ID     INT,
    IN PPrice          FLOAT,
    IN PGST            FLOAT
  )
  BEGIN

    UPDATE tBillDetailPrice a
      INNER JOIN tBillDetail b ON a.Bill_Detail_ID = b.Bill_Detail_ID
    SET a.Reconciled_Price = PPrice, a.Reconciled_GST = PGST
    WHERE b.Bill_ID = PBill_ID AND b.Transaction_ID = PTransaction_ID AND b.Object_Type = PObject_Type AND
          a.JobType_ID = PJobType_ID;

    CALL sGetTransactionStatus(1, PJobType_ID, 'JobType_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sBillStatusUpdate;
CREATE PROCEDURE sBillStatusUpdate
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PBill_ID   INT,
    IN PStatus    VARCHAR(64)
  )
  BEGIN
    UPDATE tBillHeader
    SET Status = PStatus
    WHERE Bill_ID = PBill_ID;

    CALL sGetTransactionStatus(1, PBill_ID, 'Bill_ID', NULL, NULL);
  END;


  DROP PROCEDURE IF EXISTS sBillHeaderReconcileTotalsUpdate;
CREATE PROCEDURE sBillHeaderReconcileTotalsUpdate
  (
    IN PBill_ID INT
  )
  BEGIN
    DECLARE _total_base_amount FLOAT DEFAULT 0;
    DECLARE _total_gst_amount FLOAT DEFAULT 0;

    SET _total_base_amount = (SELECT sum(Reconciled_Price)
                              FROM tBillDetailPrice a
                                INNER JOIN tBillDetail b ON a.Bill_Detail_ID = b.Bill_Detail_ID
                              WHERE b.Bill_ID = PBill_ID);
    SET _total_gst_amount = (SELECT sum(((Reconciled_Price * Reconciled_GST) / 100))
                             FROM tBillDetailPrice a
                               INNER JOIN tBillDetail b ON a.Bill_Detail_ID = b.Bill_Detail_ID
                             WHERE b.Bill_ID = PBill_ID);

    UPDATE tBillHeader
    SET Reconciled_Total_Base_Amount = _total_base_amount, Reconciled_Total_GST_Amount = _total_gst_amount
    WHERE Bill_ID = PBill_ID;

    CALL sGetTransactionStatus(1, PBill_ID, 'Bill_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sBillHeaderReconcileOtherPriceUpdate;
CREATE PROCEDURE sBillHeaderReconcileOtherPriceUpdate
  (
    IN PBill_ID     INT,
    IN POther_Price FLOAT
  )
  BEGIN
    UPDATE tBillHeader
    SET Reconciled_Other_Price = POther_Price
    WHERE Bill_ID = PBill_ID;

    CALL sGetTransactionStatus(1, PBill_ID, 'Bill_ID', NULL, NULL);
  END;


  DROP PROCEDURE IF EXISTS sBillTotalPayablesUpdate;
CREATE PROCEDURE sBillTotalPayablesUpdate
  (
    IN PEntity_ID         INT,
    IN PUser_ID           INT,
    IN PBill_ID           INT,
    IN PTotal_Base_Amount FLOAT,
    IN PGST               FLOAT,
    IN PRemarks           TEXT CHARACTER SET utf8
                          COLLATE utf8_unicode_ci,
    IN PIs_Force_Match    INT
  )
  BEGIN
    UPDATE tBillHeader
    SET
      Total_Payable_Base_Amount = PTotal_Base_Amount,
      Total_Payable_GST_Amount  = (PTotal_Base_Amount * PGST) / 100,
      Total_Payable_GST_Prcernt = PGST,
      Total_Payable_Amount      = (PTotal_Base_Amount + ((PTotal_Base_Amount * PGST) / 100)),
      Final_Remarks             = PRemarks,
      Approved_By               = PUser_ID,
      Last_Updated_On           = now(),
      Last_Updated_By           = PUser_ID,
      Status                    =
      CASE WHEN PIs_Force_Match = 1
        THEN
          'FORCEMATCHED'
      ELSE
        'MATCHED'
      END
    WHERE Bill_ID = PBill_ID;


    CALL sGetTransactionStatus(1, PBill_ID, 'Bill_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sCalendarEventAdd;
CREATE PROCEDURE sCalendarEventAdd(IN PEntiy_ID    INT,
                                   IN PUser_ID     INT,
                                   IN PEvent_Date  VARCHAR(64),
                                   IN PStart_Time  VARCHAR(64),
                                   IN PEnd_Time    VARCHAR(64),
                                   IN PTitle       TEXT,
                                   IN PDescription TEXT,
                                   IN PURL         TEXT,
                                   IN PPlace       TEXT,
                                   IN PState_ID    INT,
                                   IN PCity_ID     INT,
                                   IN PEvent_Type  VARCHAR(64))
  BEGIN

    DECLARE _id INT;
    DECLARE _start_time TIME;
    DECLARE _end_time TIME;
    DECLARE  _event_date DATE;

    IF PEvent_Date IS NULL  OR  PEvent_Date = ''
      THEN
      SET _event_date = NULL ;
        ELSE
      SET _event_date = STR_TO_DATE(PEvent_Date , '%d/%m/%Y');
    END IF;

    IF PStart_Time IS NULL OR PStart_Time = ''
    THEN
      SET _start_time = NULL;
    ELSE
      SET _start_time = TIME(PStart_Time);
    END IF;

    IF PEnd_Time IS NULL OR PEnd_Time = ''
    THEN
      SET _start_time = NULL;
    ELSE
      SET _start_time = TIME(PEnd_Time);
    END IF;

    INSERT INTO tCalendarEvent (Created_By, Event_Date, Start_Time, End_Time, Title, Description, URL, Place, Lattitude_Longitude, State_ID, City_ID, Event_Type, Last_Updated_By)
    VALUES (PUser_ID, _event_date, _start_time, _end_time, PTitle, PDescription, PURL, PPlace, NULL,
                      PState_ID, PCity_ID, PEvent_Type, PUser_ID);
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Event_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sCalendarEventEntityAdd;
CREATE PROCEDURE sCalendarEventEntityAdd(IN PEvent_ID INT, IN PEntity_Type VARCHAR(1024))
  BEGIN

    DECLARE _id INT;

    INSERT INTO tCalendarEventEntity (Event_ID, Entity_Type)
    VALUES (PEvent_ID, PEntity_Type);
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Event_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCalendarEventUpdate;
CREATE PROCEDURE sCalendarEventUpdate(

  IN PEntity_ID           INT,
  IN PUser_ID             INT,
  IN PEvent_ID            INT,
  IN PEvent_Date          VARCHAR(64),
  IN PStart_Time          VARCHAR(64),
  IN PEnd_Time            VARCHAR(64),
  IN PTitle               TEXT,
  IN PDescription         TEXT,
  IN PURL                 TEXT,
  IN PPlace               TEXT,
  IN PState_ID            INT,
  IN PCity_ID             INT,
  IN PEvent_Type          VARCHAR(64)
)
  BEGIN
    DECLARE _id INT;
    DECLARE _start_time TIME;
    DECLARE _end_time TIME;
    DECLARE _event_date DATE;

    IF PEvent_Date IS NULL OR PEvent_Date = ''
    THEN
      SET _event_date = NULL;
    ELSE
      SET _event_date = STR_TO_DATE(PEvent_Date,'%d/%m/%Y');
    END IF;

    IF PStart_Time IS NULL OR PStart_Time = ''
    THEN
      SET _start_time = NULL;
    ELSE
      SET _start_time = TIME(PStart_Time);
    END IF;

    IF PEnd_Time IS NULL OR PEnd_Time = ''
    THEN
      SET _start_time = NULL;
    ELSE
      SET _start_time = TIME(PEnd_Time);
    END IF;

    UPDATE tCalendarEvent
    SET
      Event_Date          = _event_date,
      Start_Time          = _start_time,
      End_Time            = _end_time,
      Title               = PTitle,
      Description         = PDescription,
      URL                 = PURL,
      Place               = PPlace,
      Lattitude_Longitude = NULL ,
      State_ID            = PState_ID,
      City_ID             = PCity_ID,
      Event_Type          = PEvent_Type,
      Last_Updated_On     = now(),
      Last_Updated_By     = PUser_ID
    WHERE Event_ID = PEvent_ID;

    SET _id = PEvent_ID;

    CALL sGetTransactionStatus(1, _id, 'Event_ID', NULL, NULL);


  END ;

DROP PROCEDURE IF EXISTS sCalendarEventEntityUpdate;
CREATE PROCEDURE sCalendarEventEntityUpdate(IN PEvent_ID INT, IN PEntity_Type VARCHAR(1024))
  BEGIN
    DECLARE _id INT;
    UPDATE tCalendarEventEntity
    SET
      Event_ID = PEvent_ID, Entity_Type = PEntity_Type
    WHERE Event_ID = PEvent_ID;

    SET _id = PEvent_ID;

    CALL sGetTransactionStatus(1, _id, 'Event_ID', NULL, NULL);


  END;



DROP PROCEDURE IF EXISTS sCalendarEventDelete;
CREATE PROCEDURE sCalendarEventDelete(IN PID INT)
  BEGIN
    DELETE FROM tCalendarEvent
    WHERE Event_ID = PID;
    CALL sGetTransactionStatus(1, PID, 'Event_ID', NULL, NULL);

  END;
DROP PROCEDURE IF EXISTS sCalendarEventEntityDelete;

CREATE PROCEDURE sCalendarEventEntityDelete(IN PID INT)
  BEGIN
    DELETE FROM tCalendarEventEntity
    WHERE Event_ID = PID;
    CALL sGetTransactionStatus(1, PID, 'Event_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sCalendarEventRecentOrUpcomingGetList;
CREATE PROCEDURE sCalendarEventRecentOrUpcomingGetList
  (
    IN PEntity_ID         INT,
    IN PUser_ID           INT,
    IN PFrom_Date         VARCHAR(24),
    IN PTo_Date           VARCHAR(24),
    IN PType              INT,
    IN PNumber_of_Records INT,
    IN PFY                VARCHAR(4),
    IN PSearch_Keyword    VARCHAR(128),
    IN PEntity_Type       VARCHAR(128)
  )
  BEGIN

    DECLARE fistday VARCHAR(128);
    DECLARE lastday VARCHAR(128);

    SET fistday = (SELECT fGetFirstDayOfFinancialYear());
    SET lastday = (SELECT fGetLastDayOfFinancialYear());

    IF PFrom_Date = ''
    THEN SET PFrom_Date = NULL; END IF;
    IF PTo_Date = ''
    THEN SET PTo_Date = NULL; END IF;
    IF PFY = ''
    THEN SET PFY = NULL; END IF;

    IF PNumber_of_Records IS NULL
    THEN
      SET PNumber_of_Records = 4;
    END IF;


    SELECT
      Event_ID,
      DATE_FORMAT(Created_Date, '%d/%m/%Y') AS Created_Date,
      Created_By,
      DATE_FORMAT(Event_Date, '%d/%m/%Y')   AS Event_Date,
      Start_Time,
      End_Time,
      Title,
      Description,
      URL,
      Place,
      Lattitude_Longitude,
      Last_Updated_On,
      State_ID,
      City_ID,
      Event_Type,
      Last_Updated_By
    FROM
      tCalendarEvent
    WHERE
      CASE
      WHEN PType = 1 THEN
        date(Event_Date) < curdate()
      WHEN PType = 2 THEN
        date(Event_Date) >= curdate()
      ELSE
        DATE(Event_Date) = CURDATE()
      END
      AND CASE WHEN PEntity_Type IS NOT NULL AND PEntity_Type <> ''
        THEN
          Event_ID IN (SELECT Event_ID
                       FROM tCalendarEventEntity
                       WHERE Entity_Type = PEntity_Type)
          ELSE
            1 = 1 END
    ORDER BY DATE (Event_Date) DESC
    LIMIT PNumber_of_Records;


#     SELECT
#       Event_ID,
#       DATE_FORMAT(Created_Date, '%d/%m/%Y') AS Created_Date,
#       Created_By,
#       DATE_FORMAT(Event_Date, '%d/%m/%Y')   AS Event_Date,
#       Start_Time,
#       End_Time,
#       Title,
#       Description,
#       URL,
#       Place,
#       Lattitude_Longitude,
#       Last_Updated_On,
#       State_ID,
#       City_ID,
#       Event_Type,
#       Last_Updated_By
#     FROM
#       tCalendarEvent
#     WHERE
#       CASE
#       WHEN PType = 1
#         THEN DATE(Event_Date) BETWEEN ifnull(STR_TO_DATE(PFrom_Date, '%d/%m/%Y'), DATE(fistday)) AND ifnull(
#             STR_TO_DATE(PTo_Date, '%d/%m/%Y'), CURDATE())
#       WHEN PType = 2
#         THEN DATE(Event_Date) BETWEEN ifnull(STR_TO_DATE(PFrom_Date, '%d/%m/%Y'), curdate()) AND ifnull(
#             STR_TO_DATE(PTo_Date, '%d/%m/%Y'), DATE(lastday))
#       ELSE
#         DATE(Event_Date) = CURDATE()
#       END
#       AND fGetFinacialYearByDate(Event_Date) =
#           ifnull(PFY, fGetFinacialYearByDate(CURDATE()))
#
#       AND CASE WHEN PEntity_Type IS NOT NULL AND PEntity_Type <> ''
#         THEN
#           Event_ID IN (SELECT Event_ID
#                        FROM tCalendarEventEntity
#                        WHERE Entity_Type = PEntity_Type)
#           ELSE
#             1 = 1 END
#     ORDER BY DATE (Event_Date) DESC
#     LIMIT PNumber_of_Records;
  END;


DROP PROCEDURE IF EXISTS sCalendarEventGet;
CREATE PROCEDURE sCalendarEventGet(IN PID INT)
  BEGIN
    SELECT
      Event_ID,
      Created_Date,
      Created_By,
      Event_Date,
      Start_Time,
      End_Time,
      Title,
      Description,
      URL,
      Place,
      Lattitude_Longitude,
      Last_Updated_On,
      State_ID,
      City_ID,
      Event_Type,
      Last_Updated_By
    FROM tCalendarEvent
    WHERE Event_ID = PID;
  END;

DROP PROCEDURE IF EXISTS sCalendarEventGetList;
CREATE PROCEDURE sCalendarEventGetList(IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
        Event_ID,
        Created_Date,
        Created_By,
        Event_Date,
        Start_Time,
        End_Time,
        Title,
        Description,
        URL,
        Place,
        Lattitude_Longitude,
        Last_Updated_On,
        State_ID,
        City_ID,
        Event_Type,
        Last_Updated_By

      FROM tCalendarEvent;

    ELSE
      SELECT
        Event_ID,
        Created_Date,
        Created_By,
        Event_Date,
        Start_Time,
        End_Time,
        Title,
        Description,
        URL,
        Place,
        Lattitude_Longitude,
        Last_Updated_On,
        State_ID,
        City_ID,
        Event_Type,
        Last_Updated_By

      FROM tCalendarEvent
      WHERE find_in_set(Event_ID, PIDs);
    END IF;

  END ;
#
# DROP PROCEDURE IF EXISTS sCalendarEventObjectGet;
# CREATE PROCEDURE sCalendarEventObjectGet(IN PID INT)
#   BEGIN
#     SELECT
#       Event_ID, Created_Date, Created_By, Event_Date, Start_Time, End_Time, Title, Description, URL, Place, Lattitude_Longitude, Last_Updated_On, State_ID, City_ID, Event_Type, Last_Updated_By
#     FROM tCalendarEvent
#     # WHERE CalendarEvent_ID = PCalendarEvent_ID;
#   END;
#
#
#
DROP PROCEDURE IF EXISTS sCalendarEventObjectGetList;
CREATE PROCEDURE sCalendarEventObjectGetList
  (
    IN PEntity_ID   INT(11),
    IN PUser_ID     INT,
    IN PMonth       INT,
    IN PYear        INT,
    IN PEntity_Type VARCHAR(64)
  )
  BEGIN
    SELECT
      Event_ID,
      Created_Date,
      Created_By,
      date_format(Event_Date, '%d/%m/%Y') AS Event_Date,
      Start_Time,
      End_Time,
      Title,
      Description,
      URL,
      Place,
      Lattitude_Longitude,
      Last_Updated_On,
      State_ID,
      City_ID,
      Event_Type,
      Last_Updated_By,
      Event_Date                          AS oEvent_Date
    FROM
      tCalendarEvent
    WHERE
      CASE WHEN PMonth IS NULL OR PMonth = 0
        THEN
          1 = 1
      ELSE
        MONTH(Event_Date) = PMonth
      END
      AND
      #       CASE WHEN PYear IS NULL OR PYear = ''
      #         THEN
      #           fGetFinacialYearByDate(Event_Date) = fGetFinacialYearByDate(CURDATE())
      #       ELSE
      #         fGetFinacialYearByDate(Event_Date) = PYear
      #       END AND
      CASE WHEN PYear = 0 OR PYear IS NULL
        THEN
          1 = 1
      ELSE
        year(Event_Date) = PYear
      END AND
      CASE WHEN PEntity_Type = '' OR PEntity_Type IS NULL
        THEN
          1 = 1
      ELSE
        Event_ID IN (SELECT tCalendarEventEntity.Event_ID
                     FROM tCalendarEventEntity
                     WHERE Entity_Type = PEntity_Type) OR
        Event_ID IN (SELECT x.Event_ID
                     FROM tCalendarEvent x
                     WHERE x.Event_ID NOT IN (SELECT y.Event_ID
                                              FROM tCalendarEventEntity y)) OR
        fGetEntityTypeByUserOrEntity(Created_By, 1) = PEntity_Type
      END
    ORDER BY oEvent_Date DESC;
  END;
#
#
# DROP PROCEDURE IF EXISTS sCalendarEventObjectGetListPage;
# CREATE PROCEDURE sCalendarEventObjectGetListPage(IN PEvent_ID int(11), IN PCreated_Date varchar(24), IN PCreated_By int(11), IN PEvent_Date date, IN PStart_Time time, IN PEnd_Time time, IN PTitle text, IN PDescription text, IN PURL text, IN PPlace text, IN PLattitude_Longitude varchar(1024), IN PLast_Updated_On varchar(24), IN PState_ID int(11), IN PCity_ID int(11), IN PEvent_Type varchar(64), IN PLast_Updated_By int(11) , IN PPage_Num  INT, IN PPage_Size INT)
#   BEGIN
#
#     DECLARE _offset INT DEFAULT 0;
#     DECLARE _total_rec INT DEFAULT 0;
#     DECLARE _total_pages INT DEFAULT 1;
#     SET _offset = fGetOffset(PPage_Num, PPage_Size);
#     SET _total_rec = (SELECT count(*)
#                       FROM tCalendarEvent
#       #       WHERE Status = PStatus
#
#     );
#     SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
#     IF _total_rec > 0
#     THEN
#
#       SELECT
#         _total_rec   AS total_records,
#         _total_pages AS total_pages;
#
#       SELECT
#       Event_ID, Created_Date, Created_By, Event_Date, Start_Time, End_Time, Title, Description, URL, Place, Lattitude_Longitude, Last_Updated_On, State_ID, City_ID, Event_Type, Last_Updated_By
#
#       FROM
#         tCalendarEvent
#       #       WHERE Status = PStatus
#       ORDER BY CalendarEvent_ID DESC
#       LIMIT _offset, PPage_Size;
#
#     END IF;
#   END;
#
#
DROP PROCEDURE IF EXISTS sCalendarEventEntityGetList;
CREATE PROCEDURE sCalendarEventEntityGetList(IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
      Event_ID, Entity_Type

      FROM tCalendarEventEntity;

    ELSE
      SELECT
      Event_ID, Entity_Type

      FROM tCalendarEventEntity
      WHERE find_in_set(Event_ID, PIDs);
    END IF;

  END;

DROP PROCEDURE IF EXISTS sCalendarEventAttendanceHeaderAdd;
CREATE PROCEDURE sCalendarEventAttendanceHeaderAdd
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PEvent_ID  INT(11)
  )
  BEGIN

    DECLARE _id INT;

    SET _id = (SELECT Attendance_ID
               FROM tCalendarEventAttendanceHeader
               WHERE Event_ID = PEvent_ID);

    IF _id IS NULL
    THEN
      INSERT INTO tCalendarEventAttendanceHeader (Created_On, Event_ID)
      VALUES (now(), PEvent_ID);
      SET _id = (SELECT last_insert_id());
    END IF;

    CALL sGetTransactionStatus(1, _id, 'CalendarEventAttendanceHeader_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sCalendarEventAttendanceHeaderUpdate;
CREATE PROCEDURE sCalendarEventAttendanceHeaderUpdate
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PEvent_ID  INT(11)
  )
  BEGIN
    CALL sCalendarEventAttendanceHeaderAdd(PEntity_ID, PUser_ID, PEvent_ID);
  END;

DROP PROCEDURE IF EXISTS sCalendarEventAttendanceHeaderGet;
CREATE PROCEDURE sCalendarEventAttendanceHeaderGet(IN PID INT)
  BEGIN
    SELECT
      Attendance_ID,
      Created_On,
      Event_ID
    FROM tCalendarEventAttendanceHeader
    WHERE Event_ID = PID;
  END;


DROP PROCEDURE IF EXISTS sCalendarEventAttendanceDetailAdd;
CREATE PROCEDURE sCalendarEventAttendanceDetailAdd
  (
    IN PEntity_ID       INT(11),
    IN PUser_ID         INT(11),
    IN PAttendance_ID   INT(11),
    IN PAttendance_Type INT(11))
  BEGIN

    IF exists(SELECT *
              FROM tCalendarEventAttendanceDetail
              WHERE Attendance_ID = PAttendance_ID AND Entity_ID = PEntity_ID AND User_ID = PUser_ID)
    THEN
      UPDATE tCalendarEventAttendanceDetail
      SET Attendance_Type = PAttendance_Type
      WHERE Attendance_ID = PAttendance_ID AND Entity_ID = PEntity_ID AND User_ID = PUser_ID;
    ELSE
      INSERT INTO tCalendarEventAttendanceDetail (Attendance_ID, Entity_ID, User_ID, Attendance_Type)
      VALUES (PAttendance_ID, PEntity_ID, PUser_ID, PAttendance_Type);
    END IF;
    CALL sGetTransactionStatus(1, PAttendance_ID, 'CalendarEventAttendanceDetail_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sCalendarEventAttendanceDetailUpdate;
CREATE PROCEDURE sCalendarEventAttendanceDetailUpdate
  (
    IN PEntity_ID       INT(11),
    IN PUser_ID         INT(11),
    IN PAttendance_ID   INT(11),
    IN PAttendance_Type INT(11))
  BEGIN
    CALL sCalendarEventAttendanceDetailAdd(PEntity_ID, PUser_ID, PAttendance_ID, PAttendance_Type);
  END;

DROP PROCEDURE IF EXISTS sCalendarEventAttendanceDetailGetList;
CREATE PROCEDURE sCalendarEventAttendanceDetailGetList(IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
        Attendance_ID,
        Entity_ID,
        User_ID,
        Attendance_Type
      FROM tCalendarEventAttendanceDetail;
    ELSE
      SELECT
        Attendance_ID,
        Entity_ID,
        User_ID,
        Attendance_Type
      FROM tCalendarEventAttendanceDetail
      WHERE find_in_set(Attendance_ID, PIDs);
    END IF;

  END;

DROP PROCEDURE IF EXISTS sCartItemAllocationAdd;
CREATE PROCEDURE sCartItemAllocationAdd
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PCart_Item_ID INT,
    IN PProducer_ID  INT,
    IN PQuantity     FLOAT,
    IN PIs_Approved  INT
  )
  BEGIN
    DECLARE _allocation_id INT;

    INSERT INTO tCartItemAllocation (Cart_Item_ID, Producer_ID, Quantity, Is_Approved) VALUES
      (PCart_Item_ID, PProducer_ID, PQuantity, PIs_Approved);

    SET _allocation_id = (SELECT last_insert_id());

    CALL sDirectDispatchLinkageUpdate(_allocation_id, 'ADD', PQuantity);

    CALL sGetTransactionStatus(1, _allocation_id, 'Allocation_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCartItemAllocationUpdate;
CREATE PROCEDURE sCartItemAllocationUpdate
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PCart_Item_ID INT,
    IN PProducer_ID  INT,
    IN PQuantity     FLOAT,
    IN PIs_Approved  INT
  )
  BEGIN
    DECLARE _allocation_id INT;

    UPDATE tCartItemAllocation
    SET Quantity  = PQuantity,
      Is_Approved = PIs_Approved
    WHERE Cart_Item_ID = PCart_Item_ID AND Producer_ID = PProducer_ID;

    SET _allocation_id = (SELECT Allocation_ID
                          FROM tCartItemAllocation
                          WHERE Cart_Item_ID = PCart_Item_ID AND Producer_ID = PProducer_ID
                          LIMIT 0, 1);
    CALL sDirectDispatchLinkageUpdate(_allocation_id, 'UPDATE', PQuantity);

    CALL sGetTransactionStatus(1, PCart_Item_ID, 'Cart_Item_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCartItemAllocationDelete;
CREATE PROCEDURE sCartItemAllocationDelete
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PCart_Item_ID INT,
    IN PProducer_ID  INT
  )
  BEGIN

    DECLARE _allocation_id INT;

    SET _allocation_id = (SELECT Allocation_ID
                          FROM tCartItemAllocation
                          WHERE Cart_Item_ID = PCart_Item_ID AND Producer_ID = PProducer_ID
                          LIMIT 0, 1);

    DELETE FROM tCartItemAllocation
    WHERE Cart_Item_ID = PCart_Item_ID AND Producer_ID = PProducer_ID;

    CALL sDirectDispatchLinkageUpdate(_allocation_id, 'DELETE', 0);

    CALL sGetTransactionStatus(1, PCart_Item_ID, 'Cart_Item_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCartItemAllocationGetList;
CREATE PROCEDURE sCartItemAllocationGetList
  (
    IN PCart_Item_ID INT
  )
  BEGIN
    SELECT
      Allocation_ID,
      Cart_Item_ID,
      Producer_ID,
      Quantity,
      Recycled_Quantity,
      Is_Approved
    FROM tCartItemAllocation
    WHERE Cart_Item_ID = PCart_Item_ID;
  END;

DROP PROCEDURE IF EXISTS sCartItemAllocationObjectGetList;
CREATE PROCEDURE sCartItemAllocationObjectGetList
  (
    IN PCart_Item_ID INT,
    IN PProducer_ID  INT
  )
  BEGIN
    SELECT
      Allocation_ID,
      Cart_Item_ID,
      Producer_ID,
      Quantity,
      Recycled_Quantity,
      Is_Approved
    FROM tCartItemAllocation
    WHERE Cart_Item_ID = PCart_Item_ID AND
          CASE WHEN PProducer_ID = 0 OR PProducer_ID IS NULL
            THEN
              1 = 1
          ELSE
            Producer_ID = PProducer_ID
          END;
  END;

/* Get Items list for allocation */
DROP PROCEDURE IF EXISTS sItemsForAllocationObjectGetListPage;
CREATE PROCEDURE sItemsForAllocationObjectGetListPage
  (
    IN PEntity_ID              INT,
    IN PUser_ID                INT,
    IN PFY                     INT,
    IN PFrom_Date              VARCHAR(24),
    IN PTo_Date                VARCHAR(24),
    IN PCategory_ID            INT,
    IN PParent_Category_ID     INT,
    IN PSeller_ID              INT,
    IN PIs_Approved            INT,
    IN PProducer_ID            INT,
    IN POnly_Allocated         INT,
    IN PSeller_Name_Search_Key VARCHAR(64),
    IN PBill_Number_Search_Key VARCHAR(64),
    IN PPage_Num               INT,
    IN PPage_Size              INT
  )
  BEGIN
    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;
    DECLARE _from_date DATE;
    DECLARE _to_date DATE;

    SET _offset = fGetOffset(PPage_Num, PPage_Size);

    IF PFY = 0 OR PFY IS NULL
    THEN
      SET PFY = fGetFinacialYearByDate(curdate());
    END IF;

    IF PFrom_Date = '' OR PFrom_Date IS NULL
    THEN
      SET _from_date = NULL;
    ELSE
      SET _from_date = str_to_date(PFrom_Date, '%d/%m/%Y');
    END IF;

    IF PTo_Date = '' OR PTo_Date IS NULL
    THEN
      SET _to_date = NULL;
    ELSE
      SET _to_date = str_to_date(PTo_Date, '%d/%m/%Y');
    END IF;

    IF PCategory_ID = 0
    THEN SET PCategory_ID = NULL; END IF;
    IF PParent_Category_ID = 0
    THEN SET PCategory_ID = NULL; END IF;
    IF PSeller_ID = 0
    THEN SET PSeller_ID = NULL; END IF;
    IF PIs_Approved IS NULL
    THEN SET PIs_Approved = 0; END IF;
    IF PProducer_ID = 0
    THEN SET PProducer_ID = NULL; END IF;

    SET _total_rec = (
      SELECT count(*)
      FROM tSellerBill a
        INNER JOIN tPickupRequest b ON a.Cart_ID = b.Cart_ID
        INNER JOIN tCart c ON a.Cart_ID = c.Cart_ID
        INNER JOIN tEntity d ON c.Entity_ID = d.Entity_ID
        INNER JOIN tCartItem e ON c.Cart_ID = e.Cart_ID
        INNER JOIN tCategory f ON e.Category_ID = f.Category_ID
        INNER JOIN tCategory g ON f.Parent_Category_ID = g.Category_ID
        INNER JOIN tItemHandover h ON c.Cart_ID = h.Cart_ID
        INNER JOIN tWHGRNHeader i ON h.Handover_ID = i.Handover_ID
      WHERE
        CASE WHEN POnly_Allocated = 1
          THEN
            e.Cart_Item_ID IN (SELECT Cart_Item_ID
                               FROM tCartItemAllocation)
        ELSE
          1 = 1
        END AND
        CASE WHEN PIs_Approved = 1
          THEN
            e.Cart_Item_ID IN (SELECT Cart_Item_ID
                               FROM tCartItemAllocation
                               WHERE Is_Approved = 1)
        ELSE
          e.Cart_Item_ID NOT IN (SELECT Cart_Item_ID
                                 FROM tCartItemAllocation
                                 WHERE Is_Approved = 1)
        END AND
        #         fGetFinacialYearByDate(date(a.Bill_Date)) = PFY AND
        (
          concat(a.Bill_ID, e.Category_ID) IN (
            SELECT concat(x.Bill_ID, x.Category_ID)
            FROM tCarryForwardItems x
            WHERE x.FY = 2020
          ) OR
          date(i.Transaction_Date) >= '2020-04-01'
        ) AND
        CASE WHEN _from_date = '' OR _from_date IS NULL
          THEN
            1 = 1
        ELSE
          date(a.Bill_Date) >= _from_date
        END AND
        CASE WHEN _to_date = '' OR _to_date IS NULL
          THEN
            1 = 1
        ELSE
          date(a.Bill_Date) <= _to_date
        END AND
        c.Entity_ID = ifnull(PSeller_ID, c.Entity_ID) AND
        f.Category_ID = ifnull(PCategory_ID, f.Category_ID) AND
        g.Category_ID = ifnull(PParent_Category_ID, g.Category_ID) AND
        CASE WHEN PProducer_ID = 0 OR PProducer_ID IS NULL
          THEN
            1 = 1
        ELSE
          e.Cart_Item_ID IN (SELECT Cart_Item_ID
                             FROM tCartItemAllocation
                             WHERE Producer_ID = PProducer_ID)
        END AND
        CASE WHEN PSeller_Name_Search_Key = '' OR PSeller_Name_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          d.Entity_Name LIKE concat('%', PSeller_Name_Search_Key, '%')
        END AND
        CASE WHEN PBill_Number_Search_Key = '' OR PBill_Number_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          a.Updated_Bill_Number LIKE concat('%', PBill_Number_Search_Key, '%')
        END
    );

    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);

    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        a.Bill_ID                            AS bill_id,
        a.Bill_Number                        AS bill_number,
        date_format(a.Bill_Date, '%d/%m/%Y') AS bill_date,
        a.Updated_Bill_Number                AS updated_bill_number,
        b.Finacial_Year                      AS financial_year,
        b.State_ID                           AS state_id,
        b.City_ID                            AS city_id,
        c.Entity_ID                          AS entity_id,
        c.Cart_ID                            AS cart_id,
        e.Cart_Item_ID                       AS cart_item_id,
        e.Category_ID                        AS category_id,
        f.Category_Name                      AS category_name,
        e.Category_ID                        AS parent_category_id,
        g.Category_Code                      AS parent_category_code,
        e.Inspected_Quantity                 AS quantity,
        b.WH_ID                              AS wh_id,
        a.Bill_Date                          AS obill_date
      FROM tSellerBill a
        INNER JOIN tPickupRequest b ON a.Cart_ID = b.Cart_ID
        INNER JOIN tCart c ON a.Cart_ID = c.Cart_ID
        INNER JOIN tEntity d ON c.Entity_ID = d.Entity_ID
        INNER JOIN tCartItem e ON c.Cart_ID = e.Cart_ID
        INNER JOIN tCategory f ON e.Category_ID = f.Category_ID
        INNER JOIN tCategory g ON f.Parent_Category_ID = g.Category_ID
        INNER JOIN tItemHandover h ON c.Cart_ID = h.Cart_ID
        INNER JOIN tWHGRNHeader i ON h.Handover_ID = i.Handover_ID
      WHERE
        CASE WHEN POnly_Allocated = 1
          THEN
            e.Cart_Item_ID IN (SELECT Cart_Item_ID
                               FROM tCartItemAllocation)
        ELSE
          1 = 1
        END AND
        CASE WHEN PIs_Approved = 1
          THEN
            e.Cart_Item_ID IN (SELECT Cart_Item_ID
                               FROM tCartItemAllocation
                               WHERE Is_Approved = 1)
        ELSE
          e.Cart_Item_ID NOT IN (SELECT Cart_Item_ID
                                 FROM tCartItemAllocation
                                 WHERE Is_Approved = 1)
        END AND
        -- fGetFinacialYearByDate(date(a.Bill_Date)) = PFY AND
        (
          concat(a.Bill_ID, e.Category_ID) IN (
            SELECT concat(x.Bill_ID, x.Category_ID)
            FROM tCarryForwardItems x
            WHERE x.FY = 2020
          ) OR
          date(i.Transaction_Date) >= '2020-04-01'
        ) AND
        CASE WHEN _from_date = '' OR _from_date IS NULL
          THEN
            1 = 1
        ELSE
          date(a.Bill_Date) >= _from_date
        END AND
        CASE WHEN _to_date = '' OR _to_date IS NULL
          THEN
            1 = 1
        ELSE
          date(a.Bill_Date) <= _to_date
        END AND
        c.Entity_ID = ifnull(PSeller_ID, c.Entity_ID) AND
        f.Category_ID = ifnull(PCategory_ID, f.Category_ID) AND
        g.Category_ID = ifnull(PParent_Category_ID, g.Category_ID) AND
        CASE WHEN PProducer_ID = 0 OR PProducer_ID IS NULL
          THEN
            1 = 1
        ELSE
          e.Cart_Item_ID IN (SELECT Cart_Item_ID
                             FROM tCartItemAllocation
                             WHERE Producer_ID = PProducer_ID)
        END AND
        CASE WHEN PSeller_Name_Search_Key = '' OR PSeller_Name_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          d.Entity_Name LIKE concat('%', PSeller_Name_Search_Key, '%')
        END AND
        CASE WHEN PBill_Number_Search_Key = '' OR PBill_Number_Search_Key IS NULL
          THEN
            1 = 1
        ELSE
          a.Updated_Bill_Number LIKE concat('%', PBill_Number_Search_Key, '%')
        END
      ORDER BY g.Category_Code, g.Category_Name ASC, obill_date DESC
      LIMIT _offset, PPage_Size;
    END IF;
  END;


/* Allocation - System */

DROP PROCEDURE IF EXISTS sAllocationRoundRobinUpdate;
CREATE PROCEDURE sAllocationRoundRobinUpdate()
  BEGIN
    DECLARE _max_sort_order INT;
    DECLARE _max_prod_id INT;

    SET _max_sort_order = (SELECT max(Sort_Order)
                           FROM tAllocationRoundRobin);
    SET _max_prod_id = (SELECT Producer_ID
                        FROM tAllocationRoundRobin
                        WHERE Sort_Order = _max_sort_order);

    UPDATE tAllocationRoundRobin
    SET Sort_Order = Sort_Order + 1
    WHERE Producer_ID <> _max_prod_id;
    UPDATE tAllocationRoundRobin
    SET Sort_Order = 1
    WHERE Producer_ID = _max_prod_id;

  END ;

DROP PROCEDURE IF EXISTS sAllocateToProducers;
CREATE PROCEDURE sAllocateToProducers
  (
    IN PCart_ID INT,
    IN PBill_ID INT
  )
  BEGIN
    /* Declaration Block */
    DECLARE _done INT DEFAULT 0;

    DECLARE _category_ids TEXT;
    DECLARE _c_cart_item_id INT;
    DECLARE _c_category_id INT;
    DECLARE _c_quantity FLOAT;
    DECLARE _c_bill_date DATE;

    /* Cursor Declaration */

    DECLARE _items_cursor CURSOR FOR SELECT
                                       Cart_Item_ID,
                                       Category_ID,
                                       Inspected_Quantity,
                                       date(b.Bill_Date) AS Bill_Date
                                     FROM tCartItem a
                                       INNER JOIN tSellerBill b ON a.Cart_ID = b.Cart_ID
                                     WHERE a.Cart_ID = PCart_ID;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET _done = 1;

    SELECT group_concat(Category_ID)
    INTO _category_ids
    FROM tCartItem a
    WHERE Cart_ID = PCart_ID;

    TRUNCATE tTempAllocation;

    INSERT INTO tTempAllocation
      SELECT
        y.Producer_ID,
        sum(y.Total_Target) AS Total_Target,
        count(*)            AS Total_Items,
        sum(y.Allocated)    AS Allocated
      FROM (
             SELECT
               Producer_ID,
               Category_ID,
               sum(Total_Target) AS Total_Target,
               sum(Allocated)    AS Allocated
             FROM (
                    SELECT
                      a.Producer_ID,
                      c.Category_ID,
                      sum(b.Target_Value) AS Total_Target,
                      0                   AS Allocated
                    FROM tProducerTargetHeader a
                      INNER JOIN tProducerTargetDetail b ON a.Producer_Target_ID = b.Producer_Target_ID
                      INNER JOIN tCategory c ON b.Parameter = c.Category_Code
                    WHERE a.Target_Year = 2020 AND find_in_set(c.Category_ID, _category_ids)
                    GROUP BY a.Producer_ID, c.Category_ID
                    UNION
                    SELECT
                      c.Producer_ID,
                      a.Category_ID,
                      0               AS Total_Target,
                      sum(c.Quantity) AS Allocated
                    FROM tCartItem a
                      INNER JOIN tPickupRequest b ON a.Cart_ID = b.Cart_ID
                      INNER JOIN tCartItemAllocation c ON a.Cart_Item_ID = c.Cart_Item_ID
                    WHERE
                      -- b.Finacial_Year = 2020 AND
                      find_in_set(a.Category_ID, _category_ids)
                    GROUP BY c.Producer_ID, a.Category_ID
                  ) x
             GROUP BY Producer_ID, Category_ID
             HAVING (Total_Target - Allocated) > 0
           ) y
        LEFT JOIN tAllocationRoundRobin z ON y.Producer_ID = z.Producer_ID
      GROUP BY y.Producer_ID
      ORDER BY -z.Sort_Order DESC, Total_Items DESC, (Total_Target - Allocated) DESC;

    OPEN _items_cursor;

    _item_loop: LOOP
      FETCH _items_cursor
      INTO _c_cart_item_id, _c_category_id, _c_quantity, _c_bill_date;

      IF _done = 1
      THEN
        LEAVE _item_loop;
      END IF;

      CALL sAllocateToProducersDetail(_c_cart_item_id, _c_category_id, _c_quantity, _c_bill_date);

    END LOOP;

    CLOSE _items_cursor;

    CALL sAllocationRoundRobinUpdate();

    CALL sGetTransactionStatus(1, PCart_ID, 'Cart_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sAllocateToProducersDetail;
CREATE PROCEDURE sAllocateToProducersDetail
  (
    IN PCart_Item_ID INT,
    IN PCategory_ID  INT,
    IN PQuantity     FLOAT,
    IN PInv_Date     DATE
  )
  BEGIN

    DECLARE _i_done INT DEFAULT 0;

    DECLARE _c_producer_id INT;
    DECLARE _c_total_target FLOAT;
    DECLARE _c_total_items INT;
    DECLARE _c_allocated FLOAT;

    DECLARE _prod_allocated FLOAT DEFAULT 0;
    DECLARE _prod_target FLOAT DEFAULT 0;
    DECLARE _unallocated FLOAT DEFAULT 0;

    DECLARE _qty_to_allocate FLOAT DEFAULT 0;

    DECLARE _planned FLOAT DEFAULT 0;
    DECLARE _monthly_collection FLOAT DEFAULT 0;


    DECLARE _prod_cursor CURSOR FOR SELECT
                                      Producer_ID,
                                      Total_Target,
                                      Total_Items,
                                      Allocated
                                    FROM tTempAllocation;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET _i_done = 1;

    SET _qty_to_allocate = PQuantity;

    OPEN _prod_cursor;

    _prod_loop: LOOP

      FETCH _prod_cursor
      INTO _c_producer_id, _c_total_target, _c_total_items, _c_allocated;

      IF _i_done = 1
      THEN
        LEAVE _prod_loop;
      END IF;

      SET _prod_target = 0;
      SET _prod_allocated = 0;
      SET _unallocated = 0;

      /* Check if any allocation for the month is made. If yes then allocate from there else take it from targets */
      /* And Allocated Quantity should not ne less than or equal to 0 */
      /* If Exists and Allocation is completed then move on to next producer */
      SELECT ifnull(sum(b.Quantity), 0)
      INTO _planned
      FROM tAllocationPlanHeader a
        INNER JOIN tAllocationPlanDetail b ON a.Plan_ID = b.Plan_ID
      WHERE a.Year = year(PInv_Date) AND a.Month = month(PInv_Date) AND b.Producer_ID = _c_producer_id AND
            a.Category_ID = PCategory_ID;

      SELECT ifnull(sum(a.Quantity), 0)
      INTO _monthly_collection
      FROM tCartItemAllocation a
        INNER JOIN tCartItem b ON a.Cart_Item_ID = b.Cart_Item_ID
        INNER JOIN tSellerBill c ON b.Cart_ID = c.Cart_ID
      WHERE
        b.Category_ID = PCategory_ID AND a.Producer_ID = _c_producer_id AND month(PInv_Date) = month(c.Bill_Date) AND
        year(PInv_Date) = year(c.Bill_Date);

      IF _planned <> 0 AND _planned IS NOT NULL
      THEN
        IF (_planned - _monthly_collection) > 0
        THEN
          SET _unallocated = _planned - _monthly_collection;
        END IF;
      END IF;

      /* Check if target is achieved already */

      #       IF _unallocated = 0 OR _unallocated IS NULL
      #       THEN
      SELECT ifnull(sum(a.Quantity), 0)
      INTO _prod_allocated
      FROM tCartItemAllocation a INNER JOIN tCartItem b ON a.Cart_Item_ID = b.Cart_Item_ID
        INNER JOIN tPickupRequest c ON b.Cart_ID = c.Cart_ID
      WHERE
        -- c.Finacial_Year = 2020 AND
        a.Producer_ID = _c_producer_id AND b.Category_ID = PCategory_ID;

      SELECT ifnull(b.Target_Value, 0)
      INTO _prod_target
      FROM tProducerTargetHeader a INNER JOIN tProducerTargetDetail b ON a.Producer_Target_ID = b.Producer_Target_ID
        INNER JOIN tCategory c ON b.Parameter = c.Category_Code
      WHERE a.Target_Year = 2020 AND c.Category_ID = PCategory_ID AND a.Producer_ID = _c_producer_id
      LIMIT 0, 1;

      IF _unallocated = 0 OR _unallocated IS NULL
      THEN
        SET _unallocated = _prod_target - _prod_allocated;
      ELSE
        IF _unallocated > (_prod_target - _prod_allocated)
        THEN
          SET _unallocated = _prod_target - _prod_allocated;
        END IF;
      END IF;
      #       END IF;

      IF _unallocated > 0
      THEN
        IF _qty_to_allocate <= _unallocated
        THEN
          INSERT INTO tCartItemAllocation (Cart_Item_ID, Producer_ID, Quantity, Is_Approved) VALUES
            (PCart_Item_ID, _c_producer_id, _qty_to_allocate, 0);
          SET _qty_to_allocate = _qty_to_allocate - _unallocated;
          LEAVE _prod_loop;
        ELSE
          INSERT INTO tCartItemAllocation (Cart_Item_ID, Producer_ID, Quantity, Is_Approved) VALUES
            (PCart_Item_ID, _c_producer_id, _unallocated, 0);
          SET _qty_to_allocate = _qty_to_allocate - _unallocated;
        END IF;
      END IF;


    END LOOP;

    CLOSE _prod_cursor;

  END;

DROP PROCEDURE IF EXISTS sDirectDispatchLinkageUpdate;
CREATE PROCEDURE sDirectDispatchLinkageUpdate
  (
    IN PAllocation_ID INT,
    IN PType          VARCHAR(24), -- ADD, UPDATE DELETE
    IN PQuantity      FLOAT
  )
  BEGIN
    DECLARE _is_direct INT;

    DECLARE _wh_grn_id INT;
    DECLARE _allocation_id INT;

    IF PType = 'DELETE'
    THEN
      DELETE FROM tOutwardRecycleAllocation
      WHERE Cart_Item_Allocation_ID = PAllocation_ID;
    ELSE
      SET _is_direct = (SELECT c.Is_Direct
                        FROM tCartItemAllocation a
                          INNER JOIN tCartItem b ON a.Cart_Item_ID = b.Cart_Item_ID
                          INNER JOIN tPickupRequest c ON b.Cart_ID = c.Cart_ID
                        WHERE a.Allocation_ID = PAllocation_ID
                        LIMIT 0, 1);

      IF _is_direct = 2
      THEN
        SELECT
          f.Allocation_ID,
          f.WH_GRN_ID
        INTO _allocation_id, _wh_grn_id
        FROM tCartItemAllocation a
          INNER JOIN tCartItem b ON a.Cart_Item_ID = b.Cart_Item_ID
          INNER JOIN tPickupRequest c ON b.Cart_ID = c.Cart_ID
          INNER JOIN tItemHandover d ON c.Cart_ID = d.Cart_ID
          INNER JOIN tWHGRNHeader e ON d.Handover_ID = e.Handover_ID
          INNER JOIN tOutwardAllocationDetail f ON e.WH_GRN_ID = f.WH_GRN_ID
          INNER JOIN tOutwardAllocationHeader g ON f.Allocation_ID = g.Allocation_ID AND b.Category_ID = g.Category_ID
        WHERE a.Allocation_ID = PAllocation_ID
        LIMIT 0, 1;

        IF _allocation_id IS NOT NULL AND _allocation_id <> 0
        THEN
          IF PType = 'ADD'
          THEN
            INSERT INTO tOutwardRecycleAllocation (Allocation_ID, WH_GRN_ID, Cart_Item_Allocation_ID, Quantity) VALUES
              (_allocation_id, _wh_grn_id, PAllocation_ID, PQuantity);
          ELSE
            UPDATE tOutwardRecycleAllocation
            SET Quantity = PQuantity
            WHERE
              Allocation_ID = _allocation_id AND WH_GRN_ID = _wh_grn_id AND Cart_Item_Allocation_ID = PAllocation_ID;
          END IF;

          UPDATE tCartItemAllocation
          SET Recycled_Quantity = PQuantity
          WHERE Allocation_ID = PAllocation_ID;

        END IF;
      END IF;
    END IF;

  END;


DROP PROCEDURE IF EXISTS sCartItemAllocationForReturnGetList;
CREATE PROCEDURE sCartItemAllocationForReturnGetList
  (
    IN PCart_Item_ID INT
  )
  BEGIN
    SELECT
      Allocation_ID,
      Cart_Item_ID,
      Producer_ID,
      Quantity,
      Recycled_Quantity,
      Is_Approved
    FROM tCartItemAllocation
    WHERE Cart_Item_ID = PCart_Item_ID AND (Recycled_Quantity = 0 OR Recycled_Quantity IS NULL);
  END;

DROP PROCEDURE IF EXISTS sCategoryAdd;
CREATE PROCEDURE sCategoryAdd
  (
    IN PEntity_ID          INT,
    IN PUser_ID            INT,
    IN PCategory_Code      VARCHAR(264),
    IN PCategory_Name      VARCHAR(1024),
    IN PDisplay_Name       VARCHAR(1024),
    IN PParent_Category_ID INT,
    IN PUom_ID             INT,
    IN PPrice              INT
  )
  BEGIN
    DECLARE _id INT;
    INSERT INTO tCategory (
      Category_Code, Category_Name, Display_Name, Parent_Category_ID, UOM_ID, Price, Created_By, Created_On, Last_Updated_By)
      VALUE
      (PCategory_Code, PCategory_Name, PDisplay_Name, PParent_Category_ID, PUom_ID, PPrice, PUser_ID, now(), PUser_ID);
    SET _id = LAST_INSERT_ID();
    CALL sGetTransactionStatus(1, _id, 'Category_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sCategoryUpdate;
CREATE PROCEDURE sCategoryUpdate
  (
    IN PEntiy_ID           INT,
    IN PUser_ID            INT,
    IN PCategory_ID        INT,
    IN PCategory_Code      VARCHAR(264),
    IN PCategory_Name      VARCHAR(1024),
    IN PDisplay_Name       VARCHAR(1024),
    IN PParent_Category_ID INT,
    IN PUom_ID             INT, IN PPrice INT
  )
  BEGIN
    UPDATE tCategory
    SET
      Category_ID        = PCategory_ID,
      Category_Code      = PCategory_Code,
      Category_Name      = PCategory_Name,
      Display_Name       = PDisplay_Name,
      Parent_Category_ID = PParent_Category_ID,
      UOM_ID             = PUom_ID,
      Price              = PPrice,
      Last_Updated_By    = PUser_ID
    WHERE Category_ID = PCategory_ID;
    CALL sGetTransactionStatus(1, PCategory_ID, 'Category_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCategoryDelete;
CREATE PROCEDURE sCategoryDelete(IN PCategory_ID INT)
  BEGIN

    UPDATE tCategory
    SET Is_Delete = 1
    WHERE Category_ID = PCategory_ID;

    CALL sGetTransactionStatus(1, PCategory_ID, "PCategory_ID", NULL, NULL);

  END;


DROP PROCEDURE IF EXISTS sCategoryGet;
CREATE PROCEDURE sCategoryGet(IN PCategory_ID INT)
  BEGIN
    SELECT
      Category_ID,
      Category_Code,
      Category_Name,
      Display_Name,
      Parent_Category_ID,
      UOM_ID,
      Price,
      Category_Image_Web,
      Category_Image_Phone
    FROM tCategory
    WHERE
      Category_ID = PCategory_ID
      AND Is_Delete = 0;

  END;

DROP PROCEDURE IF EXISTS sCategoryGetList;
CREATE PROCEDURE sCategoryGetList(IN PCategory_IDs TEXT)
  BEGIN
    IF PCategory_IDs = '' OR PCategory_IDs IS NULL
    THEN
      SELECT
        Category_ID,
        Category_Code,
        Category_Name,
        Display_Name,
        Parent_Category_ID,
        UOM_ID,
        Price,
        Category_Image_Web,
        Category_Image_Phone
      FROM tCategory
      WHERE Is_Delete = 0
      ORDER BY Category_Name;
    ELSE
      SELECT
        Category_ID,
        Category_Code,
        Category_Name,
        Display_Name,
        Parent_Category_ID,
        UOM_ID,
        Price,
        Category_Image_Web,
        Category_Image_Phone
      FROM tCategory
      WHERE FIND_IN_SET(Category_ID, PCategory_IDs)
            AND Is_Delete = 0
      ORDER BY Category_Name;
    END IF;

  END;


DROP PROCEDURE IF EXISTS sCategoriesByParentCategoryGetList;
CREATE PROCEDURE sCategoriesByParentCategoryGetList(IN PParent_Category_ID INT)
  BEGIN
    SELECT group_concat(Category_ID) AS Category
    FROM (
           SELECT
             Category_ID,
             Category_Code,
             Category_Name,
             Display_Name,
             Parent_Category_ID,
             UOM_ID,
             Price,
             Category_Image_Web,
             Category_Image_Phone
           FROM tCategory
           WHERE Parent_Category_ID = PParent_Category_ID
                 AND Is_Delete = 0
           ORDER BY Category_Name

         ) x;
  END;


-- DROP PROCEDURE IF EXISTS sChecklistHeaderAdd;
-- CREATE PROCEDURE sChecklistHeaderAdd( IN P_ID int(11), IN PCreated_By int(11), IN PCreated_On varchar(24), IN PObject_Type varchar(256), IN PDocument_Type varchar(256), IN PLast_Updated_By int(11), IN PLast_Updated_On varchar(24) )
--   BEGIN
--
--     DECLARE _id INT;
--
--     INSERT INTO tChecklistHeader (_ID, Created_By, Created_On, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On)
--     VALUES (P_ID, PCreated_By, PCreated_On, PObject_Type, PDocument_Type, PLast_Updated_By, PLast_Updated_On);
--     SET _id = (SELECT last_insert_id());
--
--     CALL sGetTransactionStatus(1, _id, 'ChecklistHeader_ID', NULL, NULL);
--   END;
--
-- DROP PROCEDURE IF EXISTS sChecklistHeaderUpdate;
-- CREATE PROCEDURE sChecklistHeaderUpdate(IN P_ID int(11), IN PCreated_By int(11), IN PCreated_On varchar(24), IN PObject_Type varchar(256), IN PDocument_Type varchar(256), IN PLast_Updated_By int(11), IN PLast_Updated_On varchar(24))
--   BEGIN
--     DECLARE _id INT;
--     UPDATE tChecklistHeader
--     SET
--     _ID = P_ID, Created_By = PCreated_By, Created_On = PCreated_On, Object_Type = PObject_Type, Document_Type = PDocument_Type, Last_Updated_By = PLast_Updated_By, Last_Updated_On = PLast_Updated_On
--     WHERE ChecklistHeader_ID = PChecklistHeader_ID;
--
--     SET _id = PChecklistHeader_ID;
--
--     CALL sGetTransactionStatus(1,_id, 'ChecklistHeader_ID', NULL, NULL);
--
--
--   END;
--
--
--
-- DROP PROCEDURE IF EXISTS sChecklistHeaderDelete;
-- CREATE PROCEDURE sChecklistHeaderDelete(IN PID INT)
--   BEGIN
--     DELETE FROM tChecklistHeader
--     WHERE ChecklistHeader_ID = PID;
--     CALL sGetTransactionStatus(1, PID, 'ChecklistHeader_ID', NULL, NULL);
--
--   END;
--
--
-- DROP PROCEDURE IF EXISTS sChecklistHeaderGet;
-- CREATE PROCEDURE sChecklistHeaderGet(IN PID INT)
--   BEGIN
--     SELECT
--       _ID, Created_By, Created_On, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--     FROM tChecklistHeader
--     WHERE ChecklistHeader_ID = PID;
--   END;
--
-- DROP PROCEDURE IF EXISTS sChecklistHeaderGetList;
-- CREATE PROCEDURE sChecklistHeaderGetList(IN PIDs TEXT)
--   BEGIN
--     IF PIDs = '' OR PIDs IS NULL
--     THEN
--       SELECT
--       _ID, Created_By, Created_On, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--
--       FROM tChecklistHeader;
--
--     ELSE
--       SELECT
--       _ID, Created_By, Created_On, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--
--       FROM tChecklistHeader
--       WHERE find_in_set(ChecklistHeader_ID, PIDs);
--     END IF;
--
--   END;
--
-- DROP PROCEDURE IF EXISTS sChecklistHeaderObjectGet;
-- CREATE PROCEDURE sChecklistHeaderObjectGet(IN PID INT)
--   BEGIN
--     SELECT
--       _ID, Created_By, Created_On, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--     FROM tChecklistHeader
--     # WHERE ChecklistHeader_ID = PChecklistHeader_ID;
--   END;



DROP PROCEDURE IF EXISTS sChecklistHeaderObjectGetList;
CREATE PROCEDURE sChecklistHeaderObjectGetList
  (
    IN PObject_Type   VARCHAR(256),
    IN PDocument_Type VARCHAR(256)
  )
  BEGIN

    IF PObject_Type = ''
    THEN SET PObject_Type = NULL; END IF;
    IF PDocument_Type = ''
    THEN SET PDocument_Type = NULL; END IF;

    SELECT
      _ID,
      Created_By,
      Created_On,
      Object_Type,
      Document_Type,
      Last_Updated_By,
      Last_Updated_On
    FROM
      tChecklistHeader
    WHERE Object_Type = ifnull(PObject_Type, Object_Type) AND
          Document_Type = ifnull(PDocument_Type, Document_Type)
    ORDER BY Object_Type, Document_Type DESC;
  END;


-- DROP PROCEDURE IF EXISTS sChecklistHeaderObjectGetListPage;
-- CREATE PROCEDURE sChecklistHeaderObjectGetListPage(IN P_ID int(11), IN PCreated_By int(11), IN PCreated_On varchar(24), IN PObject_Type varchar(256), IN PDocument_Type varchar(256), IN PLast_Updated_By int(11), IN PLast_Updated_On varchar(24) , IN PPage_Num  INT, IN PPage_Size INT)
--   BEGIN
--
--     DECLARE _offset INT DEFAULT 0;
--     DECLARE _total_rec INT DEFAULT 0;
--     DECLARE _total_pages INT DEFAULT 1;
--     SET _offset = fGetOffset(PPage_Num, PPage_Size);
--     SET _total_rec = (SELECT count(*)
--                       FROM tChecklistHeader
--       #       WHERE Status = PStatus
--
--     );
--     SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
--     IF _total_rec > 0
--     THEN
--
--       SELECT
--         _total_rec   AS total_records,
--         _total_pages AS total_pages;
--
--       SELECT
--       _ID, Created_By, Created_On, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--
--       FROM
--         tChecklistHeader
--       #       WHERE Status = PStatus
--       ORDER BY ChecklistHeader_ID DESC
--       LIMIT _offset, PPage_Size;
--
--     END IF;
--   END;

DROP PROCEDURE IF EXISTS sChecklistDetailObjectGetList;
CREATE PROCEDURE sChecklistDetailObjectGetList
  (
    IN PHeader_ID INT(11)
  )
  BEGIN
    SELECT
      CheckList_ID,
      Header_ID,
      Checklist,
      Checklist_Display,
      Last_Updated_By,
      Last_Updated_On
    FROM
      tChecklistDetail
    WHERE Header_ID = PHeader_ID;
  END;


DROP PROCEDURE IF EXISTS sCheckListUpdateHeaderAdd;
CREATE PROCEDURE sCheckListUpdateHeaderAdd
  (
    IN PEntity_ID      INT,
    IN PUser_ID        INT,
    IN PTransaction_ID INT(11),
    IN PObject_Type    VARCHAR(256),
    IN PDocument_Type  VARCHAR(256)
  )
  BEGIN

    DECLARE _id INT;

    INSERT INTO tCheckListUpdateHeader
    (
      Created_By,
      Created_On,
      Transaction_ID,
      Object_Type,
      Document_Type,
      Last_Updated_By,
      Last_Updated_On
    )
    VALUES
      (
        PUser_ID,
        now(),
        PTransaction_ID,
        PObject_Type,
        PDocument_Type,
        PUser_ID,
        now()
      );
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Update_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCheckListUpdateHeaderUpdate;
CREATE PROCEDURE sCheckListUpdateHeaderUpdate
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PUpdate_ID INT(11)
  )
  BEGIN
    UPDATE tCheckListUpdateHeader
    SET
      Last_Updated_By = PUser_ID,
      Last_Updated_On = now()
    WHERE Update_ID = PUpdate_ID;

    CALL sGetTransactionStatus(1, PUpdate_ID, 'Update_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sCheckListUpdateHeaderDelete;
CREATE PROCEDURE sCheckListUpdateHeaderDelete(IN PID INT)
  BEGIN
    DELETE FROM tCheckListUpdateHeader
    WHERE Update_ID = PID;
    CALL sGetTransactionStatus(1, PID, 'CheckListUpdateHeader_ID', NULL, NULL);

  END;


-- DROP PROCEDURE IF EXISTS sCheckListUpdateHeaderGet;
-- CREATE PROCEDURE sCheckListUpdateHeaderGet(IN PID INT)
--   BEGIN
--     SELECT
--       Update_ID, Created_By, Created_On, Transaction_ID, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--     FROM tCheckListUpdateHeader
--     WHERE CheckListUpdateHeader_ID = PID;
--   END;
--
-- DROP PROCEDURE IF EXISTS sCheckListUpdateHeaderGetList;
-- CREATE PROCEDURE sCheckListUpdateHeaderGetList(IN PIDs TEXT)
--   BEGIN
--     IF PIDs = '' OR PIDs IS NULL
--     THEN
--       SELECT
--       Update_ID, Created_By, Created_On, Transaction_ID, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--
--       FROM tCheckListUpdateHeader;
--
--     ELSE
--       SELECT
--       Update_ID, Created_By, Created_On, Transaction_ID, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--
--       FROM tCheckListUpdateHeader
--       WHERE find_in_set(CheckListUpdateHeader_ID, PIDs);
--     END IF;
--
--   END;
--
-- DROP PROCEDURE IF EXISTS sCheckListUpdateHeaderObjectGet;
-- CREATE PROCEDURE sCheckListUpdateHeaderObjectGet(IN PID INT)
--   BEGIN
--     SELECT
--       Update_ID, Created_By, Created_On, Transaction_ID, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--     FROM tCheckListUpdateHeader
--     # WHERE CheckListUpdateHeader_ID = PCheckListUpdateHeader_ID;
--   END;



DROP PROCEDURE IF EXISTS sCheckListUpdateHeaderObjectGetList;
CREATE PROCEDURE sCheckListUpdateHeaderObjectGetList
  (
    IN PTransaction_ID INT(11),
    IN PObject_Type    VARCHAR(256),
    IN PDocument_Type  VARCHAR(256)
  )
  BEGIN

    IF PObject_Type = ''
    THEN SET PObject_Type = NULL; END IF;
    IF PDocument_Type = ''
    THEN SET PDocument_Type = NULL; END IF;

    SELECT
      Update_ID,
      Created_By,
      Created_On,
      Transaction_ID,
      Object_Type,
      Document_Type,
      Last_Updated_By,
      Last_Updated_On
    FROM
      tCheckListUpdateHeader
    WHERE Transaction_ID = PTransaction_ID AND
          Object_Type = PObject_Type AND
          Document_Type = ifnull(PDocument_Type, Document_Type);

  END;


-- DROP PROCEDURE IF EXISTS sCheckListUpdateHeaderObjectGetListPage;
-- CREATE PROCEDURE sCheckListUpdateHeaderObjectGetListPage(IN PUpdate_ID int(11), IN PCreated_By int(11), IN PCreated_On varchar(24), IN PTransaction_ID int(11), IN PObject_Type varchar(256), IN PDocument_Type varchar(256), IN PLast_Updated_By int(11), IN PLast_Updated_On varchar(24) , IN PPage_Num  INT, IN PPage_Size INT)
--   BEGIN
--
--     DECLARE _offset INT DEFAULT 0;
--     DECLARE _total_rec INT DEFAULT 0;
--     DECLARE _total_pages INT DEFAULT 1;
--     SET _offset = fGetOffset(PPage_Num, PPage_Size);
--     SET _total_rec = (SELECT count(*)
--                       FROM tCheckListUpdateHeader
--       #       WHERE Status = PStatus
--
--     );
--     SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
--     IF _total_rec > 0
--     THEN
--
--       SELECT
--         _total_rec   AS total_records,
--         _total_pages AS total_pages;
--
--       SELECT
--       Update_ID, Created_By, Created_On, Transaction_ID, Object_Type, Document_Type, Last_Updated_By, Last_Updated_On
--
--       FROM
--         tCheckListUpdateHeader
--       #       WHERE Status = PStatus
--       ORDER BY CheckListUpdateHeader_ID DESC
--       LIMIT _offset, PPage_Size;
--
--     END IF;
--   END;


DROP PROCEDURE IF EXISTS sCheckListUpdateDetailAdd;
CREATE PROCEDURE sCheckListUpdateDetailAdd
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PUpdate_ID    INT(11),
    IN PCheckList_ID INT(11),
    IN PIs_Checked   INT(11),
    IN PComments     TEXT
  )
  BEGIN

    IF exists(SELECT *
              FROM tCheckListUpdateDetail
              WHERE Update_ID = PUpdate_ID AND CheckList_ID = PCheckList_ID)
    THEN
      UPDATE tCheckListUpdateDetail
      SET Is_Checked = PIs_Checked,
        Comments     = PComments
      WHERE Update_ID = PUpdate_ID AND CheckList_ID = PCheckList_ID;
    ELSE
      INSERT INTO tCheckListUpdateDetail (Update_ID, CheckList_ID, Is_Checked, Comments) VALUES
        (PUpdate_ID, PCheckList_ID, PIs_Checked, PComments);
    END IF;

    CALL sGetTransactionStatus(1, PCheckList_ID, 'Checklist_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sCheckListUpdateDetailUpdate;
CREATE PROCEDURE sCheckListUpdateDetailUpdate
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PUpdate_ID    INT(11),
    IN PCheckList_ID INT(11),
    IN PIs_Checked   INT(11),
    IN PComments     TEXT
  )
  BEGIN
    CALL sCheckListUpdateDetailAdd(PEntity_ID, PUser_ID, PUpdate_ID, PCheckList_ID, PIs_Checked, PComments);
  END;

DROP PROCEDURE IF EXISTS sCheckListUpdateDetailObjectGetList;
CREATE PROCEDURE sCheckListUpdateDetailObjectGetList
  (
    IN PUpdate_ID INT(11)
  )
  BEGIN
    SELECT
      Update_ID,
      CheckList_ID,
      Is_Checked,
      Comments
    FROM
      tCheckListUpdateDetail
    WHERE Update_ID = PUpdate_ID;
  END;

DROP PROCEDURE IF EXISTS sCityAdd;
CREATE PROCEDURE sCityAdd
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PState_ID     INT,
    IN PCity_Name    VARCHAR(264),
    IN PIs_Operative INT
  )
  BEGIN
    DECLARE _id INT;
    INSERT INTO tCity (State_ID, City_Name, Is_Operative)
      VALUE (PState_ID, PCity_Name, PIs_Operative);
    SET _id = LAST_INSERT_ID();
    CALL sGetTransactionStatus(1, _id, "City_ID", NULL, NULL);


  END;


DROP PROCEDURE IF EXISTS sCityUpdate;
CREATE PROCEDURE sCityUpdate
  (
    IN PEntity_ID    INT,
    IN PUser_ID      INT,
    IN PCity_ID      INT,
    IN PState_ID     INT,
    IN PCity_Name    VARCHAR(264),
    IN PIs_Operative INT
  )
  BEGIN
    UPDATE tCity
    SET
      City_ID      = PCity_ID,
      State_ID     = PState_ID,
      City_Name    = PCity_Name,
      Is_Operative = PIs_Operative
    WHERE City_ID = PCity_ID;
    CALL sGetTransactionStatus(1, PCity_ID, "City_ID", NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCityDelete;
CREATE PROCEDURE sCityDelete
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PCity_ID   INT
  )
  BEGIN
    UPDATE tCity
    SET Is_Delete = 1
    WHERE City_ID = PCity_ID;
    CALL sGetTransactionStatus(1, PCity_ID, "City_ID", NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sCityGet;
CREATE PROCEDURE sCityGet(IN PCity_ID INT)
  BEGIN
    SELECT
      City_ID,
      State_ID,
      City_Name,
      Is_Operative
    FROM tCity
    WHERE
      City_ID = PCity_ID;
  END;

DROP PROCEDURE IF EXISTS sCityGetList;
CREATE PROCEDURE sCityGetList(IN PCity_IDs TEXT)
  BEGIN
    IF PCity_IDs = '' OR PCity_IDs IS NULL
    THEN
      SELECT
        City_ID,
        State_ID,
        City_Name,
        Is_Operative
      FROM tCity
      WHERE Is_Delete = 0
      -- and Is_Operative = 1
      ORDER BY City_Name;
    ELSE
      SELECT
        City_ID,
        State_ID,
        City_Name,
        Is_Operative
      FROM tCity
      WHERE FIND_IN_SET(City_ID, PCity_IDs)
      ORDER BY City_Name;
    END IF;
  END;


DROP PROCEDURE IF EXISTS sCitiesByStateGetList;
CREATE PROCEDURE sCitiesByStateGetList(IN PState_ID INT)
  BEGIN
    SELECT group_concat(City_ID) AS City_IDs
    FROM (
           SELECT
             State_ID,
             City_ID,
             City_Name,
             Is_Operative
           FROM tCity
           WHERE State_ID = PState_ID AND Is_Delete = 0
           ORDER BY City_Name
         ) x;
  END;


DROP TABLE IF EXISTS tCollectionCentreAudit;
CREATE TABLE tCollectionCentreAudit
(
  Audit_ID             INT          AUTO_INCREMENT PRIMARY KEY,
  Created_By           INT          DEFAULT NULL,
  Created_On           DATETIME     DEFAULT now(),
  Collection_Centre_ID INT           NOT NULL,
  Audited_By           INT          DEFAULT NULL,
  Audited_By_Name      VARCHAR(1024) NOT NULL,
  Audited_On           DATE          NOT NULL,
  Audit_Type           VARCHAR(256) DEFAULT 'FREQMONTH', -- Lookup FREQTYPE
  Remarks              TEXT CHARACTER SET utf8
                       COLLATE utf8_unicode_ci,
  Last_Updated_By      INT          DEFAULT NULL,
  Last_Updated_On      DATETIME     DEFAULT now(),
  Is_Delete            INT          DEFAULT 0
);

DROP PROCEDURE IF EXISTS sCollectionCentreAuditAdd;
CREATE PROCEDURE sCollectionCentreAuditAdd
  (
    IN PEntity_ID            INT,
    IN PUser_ID              INT,
    IN PCollection_Centre_ID INT,
    IN PAudited_By           INT,
    IN PAudited_By_Name      VARCHAR(1024),
    IN PAudited_On           VARCHAR(24),
    IN PAudit_Type           VARCHAR(256),
    IN PRemarks              TEXT CHARACTER SET utf8
                             COLLATE utf8_unicode_ci
  )
  BEGIN
    DECLARE _id INT;

    INSERT INTO tCollectionCentreAudit (Created_By, Created_On, Collection_Centre_ID, Audited_By, Audited_By_Name, Audited_On, Audit_Type, Remarks, Last_Updated_By, Last_Updated_On)
    VALUES
      (PUser_ID, now(), PCollection_Centre_ID, PAudited_By, PAudited_By_Name, str_to_date(PAudited_On, '%d/%m/%Y'),
       PAudit_Type, PRemarks, PUser_ID, now());

    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Audit_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCollectionCentreAuditUpdate;
CREATE PROCEDURE sCollectionCentreAuditUpdate
  (
    IN PEntity_ID            INT,
    IN PUser_ID              INT,
    IN PAudit_ID             INT,
    IN PCollection_Centre_ID INT,
    IN PAudited_By           INT,
    IN PAudited_By_Name      VARCHAR(1024),
    IN PAudited_On           VARCHAR(24),
    IN PAudit_Type           VARCHAR(256),
    IN PRemarks              TEXT CHARACTER SET utf8
                             COLLATE utf8_unicode_ci
  )
  BEGIN

    UPDATE tCollectionCentreAudit
    SET Collection_Centre_ID = PCollection_Centre_ID,
      Audited_By             = PAudited_By,
      Audited_By_Name        = PAudited_By_Name,
      Audited_On             = str_to_date(PAudited_On, '%d/%m/%Y'),
      Audit_Type             = PAudit_Type,
      Remarks                = PRemarks,
      Last_Updated_By        = PUser_ID,
      Last_Updated_On        = now()
    WHERE Audit_ID = PAudit_ID;


    CALL sGetTransactionStatus(1, PAudit_ID, 'Audit_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCollectionCentreAuditDelete;
CREATE PROCEDURE sCollectionCentreAuditDelete
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PAudit_ID  INT
  )
  BEGIN

    UPDATE tCollectionCentreAudit
    SET Is_Delete = 1
    WHERE Audit_ID = PAudit_ID;

    CALL sGetTransactionStatus(1, PAudit_ID, 'Audit_ID', NULL, NULL);
  END;


-- DROP PROCEDURE IF EXISTS sCollectionCentreAuditGet;
-- CREATE PROCEDURE sCollectionCentreAuditGet(IN PID INT)
--   BEGIN
--     SELECT
--       Audit_ID, Created_By, Created_On, Collection_Centre_ID, Audited_By, Audited_By_Name, Audited_On, Audit_Type, Remarks, Last_Updated_By, Last_Updated_On, Is_Delete
--     FROM tCollectionCentreAudit
--     WHERE CollectionCentreAudit_ID = PID;
--   END;
--
-- DROP PROCEDURE IF EXISTS sCollectionCentreAuditGetList;
-- CREATE PROCEDURE sCollectionCentreAuditGetList(IN PIDs TEXT)
--   BEGIN
--     IF PIDs = '' OR PIDs IS NULL
--     THEN
--       SELECT
--       Audit_ID, Created_By, Created_On, Collection_Centre_ID, Audited_By, Audited_By_Name, Audited_On, Audit_Type, Remarks, Last_Updated_By, Last_Updated_On, Is_Delete
--
--       FROM tCollectionCentreAudit;
--
--     ELSE
--       SELECT
--       Audit_ID, Created_By, Created_On, Collection_Centre_ID, Audited_By, Audited_By_Name, Audited_On, Audit_Type, Remarks, Last_Updated_By, Last_Updated_On, Is_Delete
--
--       FROM tCollectionCentreAudit
--       WHERE find_in_set(CollectionCentreAudit_ID, PIDs);
--     END IF;
--
--   END;
--
-- DROP PROCEDURE IF EXISTS sCollectionCentreAuditObjectGet;
-- CREATE PROCEDURE sCollectionCentreAuditObjectGet(IN PID INT)
--   BEGIN
--     SELECT
--       Audit_ID, Created_By, Created_On, Collection_Centre_ID, Audited_By, Audited_By_Name, Audited_On, Audit_Type, Remarks, Last_Updated_By, Last_Updated_On, Is_Delete
--     FROM tCollectionCentreAudit
--     # WHERE CollectionCentreAudit_ID = PCollectionCentreAudit_ID;
--   END;
--
--
--
-- DROP PROCEDURE IF EXISTS sCollectionCentreAuditObjectGetList;
-- CREATE PROCEDURE sCollectionCentreAuditObjectGetList(IN PAudit_ID int(11), IN PCreated_By int(11), IN PCreated_On varchar(24), IN PCollection_Centre_ID int(11), IN PAudited_By int(11), IN PAudited_By_Name varchar(1024), IN PAudited_On date, IN PAudit_Type varchar(256), IN PRemarks text, IN PLast_Updated_By int(11), IN PLast_Updated_On varchar(24), IN PIs_Delete int(11))
--   BEGIN
--
--
--     SELECT
--     Audit_ID, Created_By, Created_On, Collection_Centre_ID, Audited_By, Audited_By_Name, Audited_On, Audit_Type, Remarks, Last_Updated_By, Last_Updated_On, Is_Delete
--
--     FROM
--       tCollectionCentreAudit
--     #       WHERE Status = PStatus
--     ORDER BY CollectionCentreAudit_ID DESC;
--
--   END;


DROP PROCEDURE IF EXISTS sCollectionCentreAuditObjectGetListPage;
CREATE PROCEDURE sCollectionCentreAuditObjectGetListPage
  (
    IN PEntity_ID                  INT,
    IN PUser_ID                    INT,
    IN PFrom_Date                  VARCHAR(24),
    IN PTo_Date                    VARCHAR(24),
    IN PCollection_Centre_ID       INT,
    IN PAudited_By                 INT,
    IN PAudit_Type                 VARCHAR(64),
    IN PAudited_By_Name_Key        VARCHAR(24),
    IN PCollection_Centre_Name_Key VARCHAR(24),
    IN PHierarchy_Type             VARCHAR(64),
    IN PPage_Num                   INT,
    IN PPage_Size                  INT
  )
  BEGIN

    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;

    DECLARE _from_date DATE;
    DECLARE _to_date DATE;

    IF PFrom_Date = '' OR PFrom_Date IS NULL
    THEN
      SET _from_date = NULL;
    ELSE
      SET _from_date = str_to_date(PFrom_Date, '%d/%m/%Y');
    END IF;

    IF PTo_Date = '' OR PTo_Date IS NULL
    THEN
      SET _to_date = NULL;
    ELSE
      SET _to_date = str_to_date(PTo_Date, '%d/%m/%Y');
    END IF;

    IF PCollection_Centre_ID = 0
    THEN SET PCollection_Centre_ID = NULL; END IF;

    IF PAudited_By = 0
    THEN SET PAudited_By = NULL; END IF;

    IF PAudit_Type = ''
    THEN SET PAudit_Type = NULL; END IF;


    SET _offset = fGetOffset(PPage_Num, PPage_Size);

    SET _total_rec = (SELECT count(*)
                      FROM
                        tCollectionCentreAudit
                      WHERE Audit_Type = ifnull(PAudit_Type, Audit_Type) AND
                            CASE WHEN PAudited_By IS NULL
                              THEN
                                1 = 1
                            ELSE
                              Audited_By = PAudited_By
                            END AND
                            Collection_Centre_ID = ifnull(PCollection_Centre_ID, Collection_Centre_ID) AND
                            CASE WHEN _from_date IS NULL
                              THEN
                                1 = 1
                            ELSE
                              Audited_On >= _from_date
                            END AND
                            CASE WHEN _to_date IS NULL
                              THEN
                                1 = 1
                            ELSE
                              Audited_On <= _to_date
                            END AND
                            CASE WHEN PAudited_By_Name_Key <> '' AND PAudited_By_Name_Key IS NOT NULL
                              THEN
                                Audited_By_Name LIKE concat('%', PAudited_By_Name_Key, '%')
                            ELSE
                              1 = 1
                            END AND
                            CASE WHEN PCollection_Centre_Name_Key <> '' AND PCollection_Centre_Name_Key IS NOT NULL
                              THEN
                                Collection_Centre_ID IN (SELECT Entity_ID
                                                         FROM tEntity
                                                         WHERE Entity_Type = 'ENTCOLCENTRE' AND
                                                               Entity_Name LIKE
                                                               concat('%', PCollection_Centre_Name_Key, '%'))
                            ELSE
                              1 = 1
                            END
    );

    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);

    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        Audit_ID,
        Created_By,
        Created_On,
        Collection_Centre_ID,
        Audited_By,
        Audited_By_Name,
        date_format(Audited_On, '%d/%m/%Y') AS Audited_On,
        Audit_Type,
        Remarks,
        Last_Updated_By,
        Last_Updated_On,
        Is_Delete,
        Audited_On                          AS oAudited_On
      FROM
        tCollectionCentreAudit
      WHERE Audit_Type = ifnull(PAudit_Type, Audit_Type) AND
            CASE WHEN PAudited_By IS NULL
              THEN
                1 = 1
            ELSE
              Audited_By = PAudited_By
            END AND
            Collection_Centre_ID = ifnull(PCollection_Centre_ID, Collection_Centre_ID) AND
            CASE WHEN _from_date IS NULL
              THEN
                1 = 1
            ELSE
              Audited_On >= _from_date
            END AND
            CASE WHEN _to_date IS NULL
              THEN
                1 = 1
            ELSE
              Audited_On <= _to_date
            END AND
            CASE WHEN PAudited_By_Name_Key <> '' AND PAudited_By_Name_Key IS NOT NULL
              THEN
                Audited_By_Name LIKE concat('%', PAudited_By_Name_Key, '%')
            ELSE
              1 = 1
            END AND
            CASE WHEN PCollection_Centre_Name_Key <> '' AND PCollection_Centre_Name_Key IS NOT NULL
              THEN
                Collection_Centre_ID IN (SELECT Entity_ID
                                         FROM tEntity
                                         WHERE Entity_Type = 'ENTCOLCENTRE' AND
                                               Entity_Name LIKE concat('%', PCollection_Centre_Name_Key, '%'))
            ELSE
              1 = 1
            END
      ORDER BY oAudited_On DESC
      LIMIT _offset, PPage_Size;

    END IF;
  END;


DROP PROCEDURE IF EXISTS sCollectionCentreWithLastAuditGetList;
CREATE PROCEDURE sCollectionCentreWithLastAuditGetList
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT
  )
  BEGIN
    SELECT
      Entity_ID,
      Entity_Name,
      Display_Name,
      Entity_Type,
      Address1,
      Address2,
      Address3,
      State_ID,
      City_ID,
      Pincode,
      Mobile_Number,
      Landline_Number,
      Email,
      Photo,
      Managed_By,
      Parent_Entity_ID,
      Created_By,
      Created_On,
      Currently_Managed_By,
      Is_Delete,
      Enrollment_Status,
      Entity_Status,
      NULL AS Gov_Body_Type,
      Latitude_Longitude,
      Signature,
      Is_System,
      Last_Updated_On,
      (
        SELECT date_format(max(Audited_On), '%d/%m/%Y')
        FROM tCollectionCentreAudit
        WHERE Collection_Centre_ID = Entity_ID
      )    AS Last_Audit_On
    FROM tEntity
    WHERE Entity_Type = 'ENTCOLCENTRE' AND Entity_Status = 'ACTIVE'
    ORDER BY str_to_date(Last_Audit_On, '%d/%m/%Y') DESC, Last_Audit_On is NULL ;
  END;



DROP PROCEDURE IF EXISTS sCollectionPlanAdd;
CREATE PROCEDURE sCollectionPlanAdd(
  IN PEntity_ID            INT,
  IN PUser_ID              INT,
  IN PMonth                INT,
  IN PYear                 YEAR,
  IN PCategory_ID          INT,
  IN PCity_ID              INT,
  IN PQuantity             FLOAT,
  IN PPrice                FLOAT,
  IN PQuantity_To_WH       FLOAT,
  IN PQuantity_To_Recycler FLOAT

)
  BEGIN
    DECLARE _id INT;

    SET _id = (SELECT Plan_ID
               FROM tCollectionPlan
               WHERE Month = PMonth AND Year = PYear AND Category_ID = PCategory_ID AND City_ID = PCity_ID
               LIMIT 0, 1);

    IF _id = 0 OR _id IS NULL
    THEN
      INSERT INTO tCollectionPlan (Created_By, month, year, Category_ID, City_ID, Quantity, Price, Quantity_To_WH, Quantity_To_Recycler, Last_Updated_By)
      VALUES
        (PUser_ID, PMonth, PYear, PCategory_ID, PCity_ID, PQuantity, PPrice, PQuantity_To_WH, PQuantity_To_Recycler,
         PUser_ID);

      SET _id = (SELECT last_insert_id());

      CALL sPlanningAfterCollectionChangeStatusUpdate(
          PMonth,
          PYear,
          PCategory_ID,
          PCity_ID,
          1,
          CASE WHEN PQuantity_To_WH > 0
            THEN
              1
          ELSE
            0
          END,
          CASE WHEN PQuantity_To_Recycler > 0
            THEN
              1
          ELSE
            0
          END,
          0
      );

      CALL sGetTransactionStatus(1, _id, 'Plan_ID', NULL, NULL);
    ELSE
      CALL sGetTransactionStatus(-1, _id, 'Plan_ID', 'ERROR001', 'Plan Already exists');
    END IF;
  END;


DROP PROCEDURE IF EXISTS sCollectionPlanUpdate;
CREATE PROCEDURE sCollectionPlanUpdate
  (IN PEntity_ID            INT,
   IN PUser_ID              INT,
   IN PPlan_ID              INT,
   IN PMonth                INT,
   IN PYear                 YEAR,
   IN PCategory_ID          INT,
   IN PCity_ID              INT,
   IN PQuantity             FLOAT,
   IN PPrice                FLOAT,
   IN PQuantity_To_WH       FLOAT,
   IN PQuantity_To_Recycler FLOAT
  )
  BEGIN

    DECLARE _old_quntity FLOAT DEFAULT 0;
    DECLARE _old_quntity_to_wh FLOAT DEFAULT 0;
    DECLARE _old_quntity_to_recycle FLOAT DEFAULT 0;

    SELECT
      Quantity,
      Quantity_To_WH,
      Quantity_To_Recycler
    INTO
      _old_quntity, _old_quntity_to_wh, _old_quntity_to_recycle
    FROM tCollectionPlan
    WHERE Plan_ID = PPlan_ID
    LIMIT 0, 1;

    UPDATE tCollectionPlan
    SET
      Month                = PMonth,
      Year                 = PYear,
      Category_ID          = PCategory_ID,
      City_ID              = PCity_ID,
      Quantity             = PQuantity,
      Price                = PPrice,
      Quantity_To_WH       = PQuantity_To_WH,
      Quantity_To_Recycler = PQuantity_To_Recycler,
      Last_Updated_By      = PUser_ID
    WHERE Plan_ID = PPlan_ID;

    CALL sPlanningAfterCollectionChangeStatusUpdate(
        PMonth,
        PYear,
        PCategory_ID,
        PCity_ID,
        CASE WHEN _old_quntity <> PQuantity
          THEN
            1
        ELSE
          0
        END,
        CASE WHEN _old_quntity_to_wh <> PQuantity_To_WH > 0
          THEN
            1
        ELSE
          0
        END,
        CASE WHEN _old_quntity_to_recycle <> PQuantity_To_Recycler > 0
          THEN
            1
        ELSE
          0
        END,
        0
    );


    CALL sGetTransactionStatus(1, PPlan_ID, 'Plan_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sCollectionPlanDelete;
CREATE PROCEDURE sCollectionPlanDelete(
  IN PEntity_ID INT,
  IN PUser_ID   INT,
  IN PPlan_ID   INT
)
  BEGIN

    DECLARE _month INT DEFAULT NULL;
    DECLARE _year INT DEFAULT NULL;
    DECLARE _category INT DEFAULT NULL;
    DECLARE _city INT DEFAULT NULL;

    SELECT
      Month,
      Year,
      Category_ID,
      City_ID
    INTO
      _month, _year, _category, _city
    FROM tCollectionPlan
    WHERE Plan_ID = PPlan_ID
    LIMIT 0, 1;

    DELETE FROM tCollectionPlan
    WHERE Plan_ID = PPlan_ID;

    CALL sPlanningAfterCollectionChangeStatusUpdate(
        _month,
        _year,
        _category,
        _city,
        1,
        1,
        1,
        1
    );


    CALL sGetTransactionStatus(1, PPlan_ID, 'Plan_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sCollectionPlanGetList;
CREATE PROCEDURE sCollectionPlanGetList(IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
      Plan_ID, Created_On, Created_By, Month, Year,
        Category_ID, City_ID, Quantity, Price, Quantity_To_WH, Quantity_To_Recycler, Last_Updated_By, Last_Updated_On
      FROM tCollectionPlan;
    ELSE
      SELECT
      Plan_ID, Created_On, Created_By, Month, Year,
        Category_ID, City_ID, Quantity, Price, Quantity_To_WH, Quantity_To_Recycler, Last_Updated_By, Last_Updated_On
      FROM tCollectionPlan
      WHERE find_in_set(Plan_ID,PIDs);
    END IF;
      END;



DROP PROCEDURE IF EXISTS sCollectionPlanObjectGetList;
CREATE PROCEDURE sCollectionPlanObjectGetList(
  IN PUser_ID            INT,
  IN PEntity_ID          INT,
  IN PYear               INT,
  IN PMonth              INT,

  IN PCity_ID            INT,
  IN PCategory_ID        INT,
  IN PParent_Category_ID INT
)
  BEGIN


    IF PYear = 0
    THEN SET PYear = NULL;
    END IF;

    IF PMonth = '' OR PMonth IS NULL
    THEN
      SET PMonth = NULL;
    END IF;

    SELECT
      Plan_ID,
      Created_On,
      Created_By,
      Month,
      Year,
      Category_ID,
      City_ID,
      Quantity,
      Price,
      Quantity_To_WH,
      Quantity_To_Recycler,
      Last_Updated_By,
      Last_Updated_On

    FROM
      tCollectionPlan
    WHERE

      Year = ifnull(PYear, Year) AND month = ifnull(PMonth, month) AND
      City_ID = ifnull(PCity_ID, City_ID) AND Category_ID = ifnull(Category_ID, PCategory_ID) AND
      CASE WHEN PParent_Category_ID = 0 OR PParent_Category_ID IS NULL
        THEN
          1 = 1
      ELSE
        Category_ID IN (SELECT Category_ID
                        FROM tCategory
                        WHERE Parent_Category_ID = PParent_Category_ID)
      END
    ORDER BY Plan_ID DESC;
  END;



DROP PROCEDURE IF EXISTS sCollectionPlanObjectGetListPage;
CREATE PROCEDURE sCollectionPlanObjectGetListPage(
  IN PUser_ID            INT,
  IN PEntity_ID          INT,
  IN PYear               INT,
  IN PMonth              INT,

  IN PCity_ID            INT,
  IN PCategory_ID        INT,
  IN PParent_Category_ID INT,
  IN PPage_Num           INT,
  IN PPage_Size          INT)
  BEGIN


    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;



    IF PYear = 0
    THEN SET PYear = NULL;
    END IF;

    IF PMonth = '' OR PMonth IS NULL
    THEN
      SET PMonth = NULL;
    END IF;


    SET _offset = fGetOffset(PPage_Num, PPage_Size);
    SET _total_rec = (SELECT count(*)
                      FROM tCollectionPlan
                      WHERE

                        Year = ifnull(PYear, Year) AND month = ifnull(PMonth, month) AND
                        City_ID = ifnull(PCity_ID, City_ID) AND Category_ID = ifnull(Category_ID, PCategory_ID) AND
                        CASE WHEN PParent_Category_ID = 0 OR PParent_Category_ID IS NULL
                          THEN
                            1 = 1
                        ELSE
                          Category_ID IN (SELECT Category_ID
                                          FROM tCategory
                                          WHERE Parent_Category_ID = PParent_Category_ID)
                        END

    );
    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        Plan_ID,
        Created_On,
        Created_By,
        Month,
        Year,
        Category_ID,
        City_ID,
        Quantity,
        Price,
        Quantity_To_WH,
        Quantity_To_Recycler,
        Last_Updated_By,
        Last_Updated_On

      FROM
        tCollectionPlan
      WHERE

        Year = ifnull(PYear, Year) AND month = ifnull(PMonth, month) AND
        City_ID = ifnull(PCity_ID, City_ID) AND Category_ID = ifnull(Category_ID, PCategory_ID) AND
        CASE WHEN PParent_Category_ID = 0 OR PParent_Category_ID IS NULL
          THEN
            1 = 1
        ELSE
          Category_ID IN (SELECT Category_ID
                          FROM tCategory
                          WHERE Parent_Category_ID = PParent_Category_ID)
        END
      ORDER BY Plan_ID DESC
      LIMIT _offset, PPage_Size;
    END IF;
  END;


DROP PROCEDURE IF EXISTS sCollectionPlanMonthlyGet;
CREATE PROCEDURE sCollectionPlanMonthlyGet
  (
    IN PFY INT
  )
  BEGIN
    IF PFY = 0 OR PFY IS NULL
    THEN SET PFY = (SELECT Financial_Year
                    FROM tFinancialYear
                    WHERE Is_Current = 1
                    LIMIT 0, 1);
    END IF;

    SELECT
      x.Month_Number,
      x.Month_Name,
      x.Month_Short_Name,
      ifnull(y.Planned, 0)  AS Planned,
      ifnull(y.Achieved, 0) AS Achieved
    FROM tMonth x
      LEFT JOIN (
                  SELECT
                    Month,
                    sum(Planned)  AS Planned,
                    sum(Achieved) AS Achieved
                  FROM (
                         SELECT
                           Month,
                           sum(Quantity) AS Planned,
                           0             AS Achieved
                         FROM tCollectionPlan
                         WHERE (Year = PFY AND Month >= 4) OR (Year = PFY + 1 AND Month < 4)
                         GROUP BY Month
                         UNION
                         SELECT
                           month(c.Transaction_Date) AS Month,
                           0                         AS Planned,
                           sum(d.Inward_Quantity)    AS Achieved
                         FROM tPickupRequest a
                           INNER JOIN tItemHandover b ON a.Cart_ID = b.Cart_ID
                           INNER JOIN tWHGRNHeader c ON b.Handover_ID = c.Handover_ID
                           INNER JOIN tWHGRNDetail d ON c.WH_GRN_ID = d.WH_GRN_ID
                         WHERE
                           (year(c.Transaction_Date) = PFY AND month(c.Transaction_Date >= 4)) OR
                           (year(c.Transaction_Date) = PFY + 1 AND month(c.Transaction_Date < 4))
                         GROUP BY Month) m
                  GROUP BY Month) y ON x.Month_Number = y.Month
    ORDER BY x.Sort_Order;
  END;

DROP PROCEDURE IF EXISTS sPendingCollectionGetList;
CREATE PROCEDURE sPendingCollectionGetList
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PFY        INT
  )
  BEGIN
    SELECT
      Category_ID,
      round(ifnull(sum(Target), 0), 2)   AS Target,
      round(ifnull(sum(Achieved), 0), 2) AS Achieved
    FROM (
           SELECT
             c.Category_ID  AS Category_ID,
             b.Target_Value AS Target,
             0              AS Achieved
           FROM tProducerTargetHeader a
             INNER JOIN tProducerTargetDetail b ON a.Producer_Target_ID = b.Producer_Target_ID
             INNER JOIN tCategory c ON b.Parameter = c.Category_Code
           WHERE a.Target_Year = PFY
           UNION
           SELECT
             a.Category_ID AS Category_ID,
             0             AS Target,
             a.Quantity    AS Achieved
           FROM tCarryForwardItems a
           WHERE FY = PFY
           UNION
           SELECT
             b.Category_ID     AS Category_ID,
             0                 AS Target,
             b.Inward_Quantity AS Achieved
           FROM tWHGRNHeader a
             INNER JOIN tWHGRNDetail b ON a.WH_GRN_ID = b.WH_GRN_ID
             INNER JOIN tItemHandover c ON a.Handover_ID = c.Handover_ID
             INNER JOIN tPickupRequest d ON c.Cart_ID = d.Cart_ID
           WHERE fGetFinacialYearByDate(a.Transaction_Date) = PFY
         ) x
    GROUP BY Category_ID
    HAVING Target > 0;
  END;


DROP PROCEDURE IF EXISTS sPlanningAfterCollectionChangeStatusUpdate;
CREATE PROCEDURE sPlanningAfterCollectionChangeStatusUpdate
  (
    IN PMonth                           INT,
    IN PYear                            INT,
    IN PCategory_ID                     INT,
    IN PCity_ID                         INT,
    IN PIs_Quantity_Updated             INT,
    IN PIs_Quantity_To_WH_Updated       INT,
    IN PIs_Quantity_To_Recycler_Updated INT,
    IN PIs_Delete                       INT
  )
  BEGIN

    IF PIs_Delete = 1
    THEN
      UPDATE tRecyclePlanHeader
      SET Status = 'DELETED'
      WHERE Month = PMonth AND Year = PYear AND Category_ID = PCategory_ID AND From_City_ID = PCity_ID;

      UPDATE tLogisticPlanHeader
      SET Status = 'UPDATED'
      WHERE Month = PMonth AND Year = PYear AND From_ID = PCity_ID;

      UPDATE tAllocationPlanHeader
      SET Status = 'DELETED'
      WHERE Month = PMonth AND Year = PYear AND Category_ID = PCategory_ID;

    ELSE
      /* Update Recycle Plan */
      IF PIs_Quantity_To_Recycler_Updated
      THEN
        UPDATE tRecyclePlanHeader
        SET Status = 'UPDATED'
        WHERE Month = PMonth AND Year = PYear AND Category_ID = PCategory_ID AND From_City_ID = PCity_ID;
      END IF;

      IF PIs_Quantity_To_WH_Updated
      THEN
        UPDATE tRecyclePlanHeader a
          INNER JOIN tWarehouse b ON a.From_WH_ID = b.WH_ID
        SET a.Status = 'UPDATED'
        WHERE Month = PMonth AND Year = PYear AND Category_ID = PCategory_ID AND b.City_ID = PCity_ID;
      END IF;

      /* Logistic Plan Update */
      IF PIs_Quantity_Updated = 1 OR PIs_Quantity_To_Recycler_Updated = 1 OR PIs_Quantity_To_WH_Updated = 1
      THEN
        UPDATE tLogisticPlanHeader
        SET Status = 'UPDATED'
        WHERE Month = PMonth AND Year = PYear AND From_ID = PCity_ID;
      END IF;

      /* Allocation Plan Update */
      IF PIs_Quantity_Updated
      THEN
        UPDATE tAllocationPlanHeader
        SET Status = 'UPDATED'
        WHERE Month = PMonth AND Year = PYear AND Category_ID = PCategory_ID;
      END IF;
    END IF;
  END;


DROP PROCEDURE IF EXISTS sCollectionPlanDirectGetList;
CREATE PROCEDURE sCollectionPlanDirectGetList
  (
    IN PFrom_City   INT,
    IN PCategory_ID INT
  )
  BEGIN

    IF PFrom_City = 0
    THEN SET PFrom_City = NULL; END IF;
    IF PCategory_ID = 0
    THEN SET PCategory_ID = NULL; END IF;

    SELECT
      a.Plan_ID,
      a.Created_On,
      a.Created_By,
      a.Month,
      a.Year,
      a.Category_ID,
      a.City_ID,
      a.Quantity,
      a.Price,
      a.Quantity_To_WH,
      a.Quantity_To_Recycler,
      a.Last_Updated_By,
      a.Last_Updated_On,
      b.City_Name,
      c.Category_Name,
      d.Category_Code
    FROM tCollectionPlan a
      inner join tCity b on a.City_ID = b.City_ID
      inner join tCategory c on a.Category_ID = c.Category_ID
      inner join tCategory d on c.Parent_Category_ID = d.Category_ID
    WHERE
      Quantity_To_Recycler > 0 AND
      Year = year(curdate()) AND
      month = Month(curdate()) AND
      a.City_ID = ifnull(PFrom_City, a.City_ID) AND
      a.Category_ID = ifnull(PCategory_ID, a.Category_ID)
    ORDER BY b.City_Name;
  END;

DROP PROCEDURE IF EXISTS sCommentAdd;
CREATE PROCEDURE sCommentAdd
  (
    IN PEntity_ID         INT,
    IN PUser_ID           INT,
    IN PComment_Master_ID INT(11),
    IN PComments          TEXT,
    IN PTransaction_ID    INT(11),
    IN PObject_Type       VARCHAR(1024),
    IN PDocument_Type     VARCHAR(1024),
    IN PInternal_Comments TEXT,
    IN PExternal_Comments TEXT
  )
  BEGIN


    DECLARE _id INT DEFAULT NULL;

    SET _id = (SELECT Comment_ID
               FROM tComment
               WHERE Transaction_ID = PTransaction_ID
                     AND Object_Type = PObject_Type
                     AND CASE WHEN PDocument_Type = '' OR PDocument_Type IS NULL
                 THEN
                   (Document_Type = '' OR Document_Type IS NULL)
                         ELSE
                           Document_Type = PDocument_Type
                         END
               LIMIT 0, 1);

    IF _id = 0 OR _id IS NULL
    THEN
      INSERT INTO tComment
      (
        Created_By,
        Created_On,
        Comment_Master_ID,
        Comments,
        Transaction_ID,
        Object_Type,
        Document_Type,
        Internal_Comments,
        External_Comments,
        Last_Updated_On,
        Last_Updated_By,
        Is_Delete
      )
      VALUES
        (
          PUser_ID,
          now(),
          PComment_Master_ID,
          PComments,
          PTransaction_ID,
          PObject_Type,
          PDocument_Type,
          PInternal_Comments,
          PExternal_Comments,
          now(),
          PUser_ID,
          0);
      SET _id = (SELECT last_insert_id());

      CALL sGetTransactionStatus(1, _id, 'Comment_ID', NULL, NULL);
    ELSE
      CALL sCommentUpdate(PEntity_ID, PUser_ID, _id, PComment_Master_ID, PComments, PInternal_Comments,
                          PExternal_Comments);
    END IF;
  END;


DROP PROCEDURE IF EXISTS sCommentUpdate;
CREATE PROCEDURE sCommentUpdate
  (
    IN PEntity_ID         INT,
    IN PUser_ID           INT,
    IN PComment_ID        INT(11),
    IN PComment_Master_ID INT(11),
    IN PComments          TEXT,
    IN PInternal_Comments TEXT,
    IN PExternal_Comments TEXT
  )
  BEGIN
    UPDATE tComment
    SET
      Comment_Master_ID = PComment_Master_ID,
      Comments          = PComments,
      Internal_Comments = PInternal_Comments,
      External_Comments = PExternal_Comments,
      Last_Updated_On   = now(),
      Last_Updated_By   = PUser_ID
    WHERE Comment_ID = PComment_ID;

    CALL sGetTransactionStatus(1, PComment_ID, 'Comment_ID', NULL, NULL);
  END;



DROP PROCEDURE IF EXISTS sCommentDelete;
CREATE PROCEDURE sCommentDelete(IN PComment_ID INT)
  BEGIN
    UPDATE tComment
    SET Is_Delete = 1
    WHERE Comment_ID = PComment_ID;

    CALL sGetTransactionStatus(1, PComment_ID, 'Comment_ID', NULL, NULL);

  END;


DROP PROCEDURE IF EXISTS sCommentGet;
CREATE PROCEDURE sCommentGet(IN PID INT)
  BEGIN
    SELECT
      Comment_ID, Created_By, Created_On, Comment_Master_ID, Comments, Transaction_ID, Object_Type, Document_Type, Internal_Comments, External_Comments, Last_Updated_On, Last_Updated_By, Is_Delete
    FROM tComment
    WHERE Comment_ID = PID;
  END;

DROP PROCEDURE IF EXISTS sCommentGetList;
CREATE PROCEDURE sCommentGetList(IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
      Comment_ID, Created_By, Created_On, Comment_Master_ID, Comments, Transaction_ID, Object_Type, Document_Type, Internal_Comments, External_Comments, Last_Updated_On, Last_Updated_By, Is_Delete

      FROM tComment;

    ELSE
      SELECT
      Comment_ID, Created_By, Created_On, Comment_Master_ID, Comments, Transaction_ID, Object_Type, Document_Type, Internal_Comments, External_Comments, Last_Updated_On, Last_Updated_By, Is_Delete

      FROM tComment
      WHERE find_in_set(Comment_ID, PIDs);
    END IF;

  END;

DROP PROCEDURE IF EXISTS sCommentObjectGet;
CREATE PROCEDURE sCommentObjectGet(IN PID INT)
  BEGIN
    SELECT
      Comment_ID, Created_By, Created_On, Comment_Master_ID, Comments, Transaction_ID, Object_Type, Document_Type, Internal_Comments, External_Comments, Last_Updated_On, Last_Updated_By, Is_Delete
    FROM tComment
    WHERE Comment_ID = PComment_ID;
  END;



DROP PROCEDURE IF EXISTS sCommentObjectGetList;
CREATE PROCEDURE sCommentObjectGetList(IN PEntity_ID     INT, IN PUser_ID INT, IN PTransaction_ID INT,
                                       IN PObject_Type   VARCHAR(1024),
                                       IN PDocument_Type VARCHAR(1024))
  BEGIN
    IF PObject_Type = ''
    THEN SET PObject_Type = NULL; END IF;
    IF PDocument_Type = ''
    THEN SET PDocument_Type = NULL; END IF;

    SELECT
      Comment_ID,
      Created_By,
      Created_On,
      Comment_Master_ID,
      Comments,
      Transaction_ID,
      Object_Type,
      Document_Type,
      Internal_Comments,
      External_Comments,
      Last_Updated_On,
      Last_Updated_By,
      Is_Delete
    FROM
      tComment
    WHERE
      Is_Delete = 0 AND
      Transaction_ID = PTransaction_ID AND
      CASE WHEN PObject_Type IS NULL
        THEN 1 = 1
      ELSE Object_Type = PObject_Type END
      AND
      CASE WHEN PDocument_Type IS NULL
        THEN 1 = 1
      ELSE Document_Type = PDocument_Type END
    ORDER BY Comment_ID DESC;

  END;


DROP PROCEDURE IF EXISTS sCommentObjectGetListPage;
CREATE PROCEDURE sCommentObjectGetListPage(IN PComment_ID int(11), IN PCreated_By int(11), IN PCreated_On varchar(24), IN PComment_Master_ID int(11), IN PComments text, IN PTransaction_ID int(11), IN PObject_Type varchar(1024), IN PDocument_Type varchar(1024), IN PInternal_Comments text, IN PExternal_Comments text, IN PLast_Updated_On varchar(24), IN PLast_Updated_By int(11), IN PIs_Delete int(11) , IN PPage_Num  INT, IN PPage_Size INT)
  BEGIN

    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;
    SET _offset = fGetOffset(PPage_Num, PPage_Size);
    SET _total_rec = (SELECT count(*)
                      FROM tComment
      #       WHERE Status = PStatus

    );
    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
      Comment_ID, Created_By, Created_On, Comment_Master_ID, Comments, Transaction_ID, Object_Type, Document_Type, Internal_Comments, External_Comments, Last_Updated_On, Last_Updated_By, Is_Delete

      FROM
        tComment
      #       WHERE Status = PStatus
      ORDER BY Comment_ID DESC
      LIMIT _offset, PPage_Size;

    END IF;
  END;


DROP PROCEDURE IF EXISTS sCommentHistoryAdd;
CREATE PROCEDURE sCommentHistoryAdd
  (
    IN PEntiy_ID          INT,
    IN PUser_ID           INT,
    IN PComment_ID        INT,
    IN PComment_Master_ID INT,
    IN PComments          TEXT,
    IN PInternal_Comments TEXT,
    IN PExternal_Comments TEXT
  )
  BEGIN

    DECLARE _id INT;

    INSERT INTO tCommentHistory (Comment_ID, Updated_By, Updated_On, Comment_Master_ID, Comments, Internal_Comments, External_Comments)
    VALUES
      (PComment_ID, PUser_ID, now(), PComment_Master_ID, PComments, PInternal_Comments,
       PExternal_Comments);
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'CommentHistory_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sCommentHistoryGetList;
CREATE PROCEDURE sCommentHistoryGetList(IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
        Comment_History_ID,
        Comment_ID,
        Updated_By,
        date_format(Updated_On, '%d/%m/%Y') AS Updated_On,
        Comment_Master_ID,
        Comments,
        Internal_Comments,
        External_Comments
      FROM tCommentHistory;
    ELSE
      SELECT
        Comment_History_ID,
        Comment_ID,
        Updated_By,
        date_format(Updated_On, '%d/%m/%Y') AS Updated_On,
        Comment_Master_ID,
        Comments,
        Internal_Comments,
        External_Comments
      FROM tCommentHistory
      WHERE find_in_set(Comment_ID, PIDs);
    END IF;
  END;

-- DROP PROCEDURE IF EXISTS sCommentMasterAdd;
-- CREATE PROCEDURE sCommentMasterAdd( IN PComment_Master_ID int(11), IN PCreated_By int(11), IN PCreated_On varchar(24), IN PComment text, IN PComment_Display text, IN PObject_Type varchar(1024), IN PDocument_Type varchar(1024), IN PIs_Visible int(11) )
--   BEGIN
--
--     DECLARE _id INT;
--
--     INSERT INTO tCommentMaster (Comment_Master_ID, Created_By, Created_On, Comment, Comment_Display, Object_Type, Document_Type, Is_Visible)
--     VALUES (PComment_Master_ID, PCreated_By, PCreated_On, PComment, PComment_Display, PObject_Type, PDocument_Type, PIs_Visible);
--     SET _id = (SELECT last_insert_id());
--
--     CALL sGetTransactionStatus(1, _id, 'CommentMaster_ID', NULL, NULL);
--   END;
--
-- DROP PROCEDURE IF EXISTS sCommentMasterUpdate;
-- CREATE PROCEDURE sCommentMasterUpdate(IN PComment_Master_ID int(11), IN PCreated_By int(11), IN PCreated_On varchar(24), IN PComment text, IN PComment_Display text, IN PObject_Type varchar(1024), IN PDocument_Type varchar(1024), IN PIs_Visible int(11))
--   BEGIN
--     DECLARE _id INT;
--     UPDATE tCommentMaster
--     SET
--     Comment_Master_ID = PComment_Master_ID, Created_By = PCreated_By, Created_On = PCreated_On, Comment = PComment, Comment_Display = PComment_Display, Object_Type = PObject_Type, Document_Type = PDocument_Type, Is_Visible = PIs_Visible
--     WHERE CommentMaster_ID = PCommentMaster_ID;
--
--     SET _id = PCommentMaster_ID;
--
--     CALL sGetTransactionStatus(1,_id, 'CommentMaster_ID', NULL, NULL);
--
--
--   END;
--
--
--
-- DROP PROCEDURE IF EXISTS sCommentMasterDelete;
-- CREATE PROCEDURE sCommentMasterDelete(IN PID INT)
--   BEGIN
--     DELETE FROM tCommentMaster
--     WHERE CommentMaster_ID = PID;
--     CALL sGetTransactionStatus(1, PID, 'CommentMaster_ID', NULL, NULL);
--
--   END;
--
--
-- DROP PROCEDURE IF EXISTS sCommentMasterGet;
-- CREATE PROCEDURE sCommentMasterGet(IN PID INT)
--   BEGIN
--     SELECT
--       Comment_Master_ID, Created_By, Created_On, Comment, Comment_Display, Object_Type, Document_Type, Is_Visible
--     FROM tCommentMaster
--     WHERE CommentMaster_ID = PID;
--   END;
--
-- DROP PROCEDURE IF EXISTS sCommentMasterGetList;
-- CREATE PROCEDURE sCommentMasterGetList(IN PIDs TEXT)
--   BEGIN
--     IF PIDs = '' OR PIDs IS NULL
--     THEN
--       SELECT
--       Comment_Master_ID, Created_By, Created_On, Comment, Comment_Display, Object_Type, Document_Type, Is_Visible
--
--       FROM tCommentMaster;
--
--     ELSE
--       SELECT
--       Comment_Master_ID, Created_By, Created_On, Comment, Comment_Display, Object_Type, Document_Type, Is_Visible
--
--       FROM tCommentMaster
--       WHERE find_in_set(CommentMaster_ID, PIDs);
--     END IF;
--
--   END;
--
-- DROP PROCEDURE IF EXISTS sCommentMasterObjectGet;
-- CREATE PROCEDURE sCommentMasterObjectGet(IN PID INT)
--   BEGIN
--     SELECT
--       Comment_Master_ID, Created_By, Created_On, Comment, Comment_Display, Object_Type, Document_Type, Is_Visible
--     FROM tCommentMaster
--     # WHERE CommentMaster_ID = PCommentMaster_ID;
--   END;



DROP PROCEDURE IF EXISTS sCommentMasterObjectGetList;
CREATE PROCEDURE sCommentMasterObjectGetList(IN PEntity_ID     INT, IN PUser_ID INT, IN PObject_Type VARCHAR(1024),
                                             IN PDocument_Type VARCHAR(1024))
  BEGIN
    SELECT
      Comment_Master_ID,
      Created_By,
      Created_On,
      Comment,
      Comment_Display,
      Object_Type,
      Document_Type,
      Is_Visible
    FROM
      tCommentMaster
    WHERE
      CASE WHEN PObject_Type IS NULL
        THEN 1 = 1
      ELSE Object_Type = PObject_Type OR Object_Type IS NULL END
      AND
      CASE WHEN PDocument_Type IS NULL
        THEN 1 = 1
      ELSE Document_Type = PDocument_Type OR Document_Type IS NULL END
      AND Is_Visible = 1
    ORDER BY Comment_Master_ID DESC;

  END;


-- DROP PROCEDURE IF EXISTS sCommentMasterObjectGetListPage;
-- CREATE PROCEDURE sCommentMasterObjectGetListPage(IN PComment_Master_ID int(11), IN PCreated_By int(11), IN PCreated_On varchar(24), IN PComment text, IN PComment_Display text, IN PObject_Type varchar(1024), IN PDocument_Type varchar(1024), IN PIs_Visible int(11) , IN PPage_Num  INT, IN PPage_Size INT)
--   BEGIN
--
--     DECLARE _offset INT DEFAULT 0;
--     DECLARE _total_rec INT DEFAULT 0;
--     DECLARE _total_pages INT DEFAULT 1;
--     SET _offset = fGetOffset(PPage_Num, PPage_Size);
--     SET _total_rec = (SELECT count(*)
--                       FROM tCommentMaster
--       #       WHERE Status = PStatus
--
--     );
--     SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
--     IF _total_rec > 0
--     THEN
--
--       SELECT
--         _total_rec   AS total_records,
--         _total_pages AS total_pages;
--
--       SELECT
--       Comment_Master_ID, Created_By, Created_On, Comment, Comment_Display, Object_Type, Document_Type, Is_Visible
--
--       FROM
--         tCommentMaster
--       #       WHERE Status = PStatus
--       ORDER BY CommentMaster_ID DESC
--       LIMIT _offset, PPage_Size;
--
--     END IF;
--   END;




DROP PROCEDURE IF EXISTS sDailyPlanAdd;
CREATE PROCEDURE sDailyPlanAdd
  (
    IN PEntity_ID     INT,
    IN PUser_ID       INT,
    IN PCity_ID       INT,
    IN PDispatch_Date VARCHAR(24),
    IN PDispatch_Type VARCHAR(24),
    IN PRecycler_ID   INT,
    IN PLSP_ID        INT,
    IN POther_LSP     VARCHAR(264),
    IN PCategory_ID   INT,
    IN PTent_Quantity FLOAT,
    IN PQuantity      FLOAT,
    IN PType          VARCHAR(64),
    IN PStatus        VARCHAR(64),
    IN PRemarks       TEXT CHARACTER SET utf8
                      COLLATE utf8_unicode_ci
  )
  BEGIN
    DECLARE _id INT;

    INSERT INTO tDailyPlan (Created_By, Created_On, City_ID, Dispatch_Date, Dispatch_Type, Recycler_ID, LSP_ID, Other_LSP, Category_ID, Tentative_Quantity, Quantity, Status, Type, Remarks, Last_Updated_On, Last_Updated_By)
    VALUES
      (PUser_ID, now(), PCity_ID, str_to_date(PDispatch_Date, '%d/%m/%Y'), PDispatch_Type, PRecycler_ID, PLSP_ID,
                 POther_LSP, PCategory_ID, PTent_Quantity, PQuantity, PStatus, PType, PRemarks, now(), PUser_ID);

    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Plan_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDailyPlanUpdate;
CREATE PROCEDURE sDailyPlanUpdate
  (
    IN PEntity_ID     INT,
    IN PUser_ID       INT,
    IN PPlan_ID       INT,
    IN PCity_ID       INT,
    IN PDispatch_Date VARCHAR(24),
    IN PDispatch_Type VARCHAR(24),
    IN PRecycler_ID   INT,
    IN PLSP_ID        INT,
    IN POther_LSP     VARCHAR(264),
    IN PCategory_ID   INT,
    IN PTent_Quantity FLOAT,
    IN PQuantity      FLOAT,
    IN PType          VARCHAR(64),
    IN PStatus        VARCHAR(64),
    IN PRemarks       TEXT CHARACTER SET utf8
                      COLLATE utf8_unicode_ci
  )
  BEGIN

    UPDATE tDailyPlan
    SET
      City_ID            = PCity_ID,
      Dispatch_Date      = str_to_date(PDispatch_Date, '%d/%m/%Y'),
      Dispatch_Type      = PDispatch_Type,
      Recycler_ID        = PRecycler_ID,
      LSP_ID             = PLSP_ID,
      Other_LSP          = POther_LSP,
      Category_ID        = PCategory_ID,
      Tentative_Quantity = PTent_Quantity,
      Quantity           = PQuantity,
      Type               = PType,
      Status             = PStatus,
      Remarks            = PRemarks,
      Last_Updated_By    = PUser_ID,
      Last_Updated_On    = now()
    WHERE Plan_ID = PPlan_ID;

    CALL sGetTransactionStatus(1, PPlan_ID, 'Plan_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDailyPlanDelete;
CREATE PROCEDURE sDailyPlanDelete
  (
    IN PEntity_ID INT,
    IN PUser_ID   INT,
    IN PPlan_ID   INT
  )
  BEGIN
    DELETE FROM tDailyPlan
    WHERE Plan_ID = PPlan_ID;

    CALL sGetTransactionStatus(1, PPlan_ID, 'Plan_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDailyPlanObjectGetList;
CREATE PROCEDURE sDailyPlanObjectGetList
  (
    IN PEntity_ID             INT,
    IN PUser_ID               INT,
    IN PFrom_Date             VARCHAR(24),
    IN PTo_Date               VARCHAR(24),
    IN PCity_ID               INT,
    IN PDispatch_Type         VARCHAR(24),
    IN PRecycler_ID           INT,
    IN PLSP_ID                INT,
    IN PCategory_ID           INT,
    IN PParent_Category_ID    INT,
    IN POther_LSP_Name_Search VARCHAR(264),
    IN PType                  VARCHAR(64),
    IN PStatus                VARCHAR(64)
  )
  BEGIN

    DECLARE _from_date DATE DEFAULT NULL;
    DECLARE _to_date DATE DEFAULT NULL;


    IF PFrom_Date <> '' AND PFrom_Date IS NOT NULL
    THEN
      SET _from_date = str_to_date(PFrom_Date, '%d/%m/%Y');
    END IF;

    IF PTo_Date <> '' AND PTo_Date IS NOT NULL
    THEN
      SET _to_date = str_to_date(PTo_Date, '%d/%m/%Y');
    END IF;

    IF PCategory_ID = 0
    THEN SET PCategory_ID = NULL; END IF;
    IF PParent_Category_ID = 0
    THEN SET PParent_Category_ID = NULL; END IF;
    IF PType = ''
    THEN SET PType = NULL; END IF;

    SELECT
      a.Plan_ID,
      a.Created_By,
      a.Created_On,
      a.City_ID,
      a.Dispatch_Date,
      a.Dispatch_Type,
      a.Recycler_ID,
      a.LSP_ID,
      a.Other_LSP,
      a.Category_ID,
      a.Tentative_Quantity,
      a.Quantity,
      a.Type,
      a.Status,
      a.Remarks,
      a.Last_Updated_On,
      a.Last_Updated_By
    FROM tDailyPlan a
      INNER JOIN tCategory b ON a.Category_ID = b.Category_ID
      INNER JOIN tCategory c ON b.Parent_Category_ID = c.Category_ID
    WHERE
      a.Type = ifnull(PType, a.Type) AND
      CASE WHEN PStatus = '' OR PStatus IS NULL
        THEN
          1 = 1
      ELSE
        Status = PStatus
      END AND
      CASE WHEN _from_date IS NULL
        THEN
          1 = 1
      ELSE
        date(Dispatch_Date) >= _from_date
      END AND
      CASE WHEN _to_date IS NULL
        THEN
          1 = 1
      ELSE
        date(Dispatch_Date) <= _to_date
      END AND
      City_ID = ifnull(PCity_ID, City_ID) AND
      CASE WHEN PDispatch_Type = '' OR PDispatch_Type IS NULL
        THEN
          1 = 1
      ELSE
        Dispatch_Type = PDispatch_Type
      END AND
      CASE WHEN PRecycler_ID = 0 OR PRecycler_ID IS NULL
        THEN
          1 = 1
      ELSE
        Recycler_ID = PRecycler_ID
      END AND
      CASE WHEN PLSP_ID = 0 OR PLSP_ID IS NULL
        THEN
          1 = 1
      ELSE
        LSP_ID = PLSP_ID
      END AND
      a.Category_ID = ifnull(PCategory_ID, a.Category_ID) AND
      b.Parent_Category_ID = ifnull(PParent_Category_ID, b.Parent_Category_ID) AND
      CASE WHEN POther_LSP_Name_Search = '' OR POther_LSP_Name_Search IS NULL
        THEN
          1 = 1
      ELSE
        Other_LSP LIKE concat('%', POther_LSP_Name_Search, '%')
      END
    ORDER BY a.Last_Updated_On DESC;

  END;

DROP PROCEDURE IF EXISTS sDailyPlanObjectGetListPage;
CREATE PROCEDURE sDailyPlanObjectGetListPage
  (
    IN PEntity_ID             INT,
    IN PUser_ID               INT,
    IN PFrom_Date             VARCHAR(24),
    IN PTo_Date               VARCHAR(24),
    IN PCity_ID               INT,
    IN PDispatch_Type         VARCHAR(24),
    IN PRecycler_ID           INT,
    IN PLSP_ID                INT,
    IN PCategory_ID           INT,
    IN PParent_Category_ID    INT,
    IN POther_LSP_Name_Search VARCHAR(264),
    IN PType                  VARCHAR(64),
    IN PStatus                VARCHAR(64),
    IN PPage_Num              INT,
    IN PPage_Size             INT
  )
  BEGIN
    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;
    DECLARE _from_date DATE;
    DECLARE _to_date DATE;

    SET _offset = fGetOffset(PPage_Num, PPage_Size);

    IF PFrom_Date <> '' AND PFrom_Date IS NOT NULL
    THEN
      SET _from_date = str_to_date(PFrom_Date, '%d/%m/%Y');
    END IF;

    IF PTo_Date <> '' AND PTo_Date IS NOT NULL
    THEN
      SET _to_date = str_to_date(PTo_Date, '%d/%m/%Y');
    END IF;

    IF PCategory_ID = 0
    THEN SET PCategory_ID = NULL; END IF;
    IF PParent_Category_ID = 0
    THEN SET PParent_Category_ID = NULL; END IF;
    IF PType = ''
    THEN SET PType = NULL; END IF;

    SET _total_rec = (
      SELECT count(*)
      FROM tDailyPlan a
        INNER JOIN tCategory b ON a.Category_ID = b.Category_ID
        INNER JOIN tCategory c ON b.Parent_Category_ID = c.Category_ID
      WHERE
        a.Type = ifnull(PType, a.Type) AND
        CASE WHEN PStatus = '' OR PStatus IS NULL
          THEN
            1 = 1
        ELSE
          a.Status = PStatus
        END AND
        CASE WHEN _from_date IS NULL
          THEN
            1 = 1
        ELSE
          date(Dispatch_Date) >= _from_date
        END AND
        CASE WHEN _to_date IS NULL
          THEN
            1 = 1
        ELSE
          date(Dispatch_Date) <= _to_date
        END AND
        City_ID = ifnull(PCity_ID, City_ID) AND
        CASE WHEN PDispatch_Type = '' OR PDispatch_Type IS NULL
          THEN
            1 = 1
        ELSE
          Dispatch_Type = PDispatch_Type
        END AND
        CASE WHEN PRecycler_ID = 0 OR PRecycler_ID IS NULL
          THEN
            1 = 1
        ELSE
          Recycler_ID = PRecycler_ID
        END AND
        CASE WHEN PLSP_ID = 0 OR PLSP_ID IS NULL
          THEN
            1 = 1
        ELSE
          LSP_ID = PLSP_ID
        END AND
        a.Category_ID = ifnull(PCategory_ID, a.Category_ID) AND
        b.Parent_Category_ID = ifnull(PParent_Category_ID, b.Parent_Category_ID) AND
        CASE WHEN POther_LSP_Name_Search = '' OR POther_LSP_Name_Search IS NULL
          THEN
            1 = 1
        ELSE
          Other_LSP LIKE concat('%', POther_LSP_Name_Search, '%')
        END
    );

    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);

    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        a.Plan_ID,
        a.Created_By,
        a.Created_On,
        a.City_ID,
        date_format(a.Dispatch_Date, '%d/%m/%Y') AS Dispatch_Date,
        a.Dispatch_Type,
        a.Recycler_ID,
        a.LSP_ID,
        a.Other_LSP,
        a.Category_ID,
        a.Tentative_Quantity,
        a.Quantity,
        a.Type,
        a.Status,
        a.Remarks,
        a.Last_Updated_On,
        a.Last_Updated_By,
        a.Dispatch_Date                          AS oDispatch_Date
      FROM tDailyPlan a
        INNER JOIN tCategory b ON a.Category_ID = b.Category_ID
        INNER JOIN tCategory c ON b.Parent_Category_ID = c.Category_ID
      WHERE
        a.Type = ifnull(PType, a.Type) AND
        CASE WHEN PStatus = '' OR PStatus IS NULL
          THEN
            1 = 1
        ELSE
          a.Status = PStatus
        END AND
        CASE WHEN _from_date IS NULL
          THEN
            1 = 1
        ELSE
          date(Dispatch_Date) >= _from_date
        END AND
        CASE WHEN _to_date IS NULL
          THEN
            1 = 1
        ELSE
          date(Dispatch_Date) <= _to_date
        END AND
        City_ID = ifnull(PCity_ID, City_ID) AND
        CASE WHEN PDispatch_Type = '' OR PDispatch_Type IS NULL
          THEN
            1 = 1
        ELSE
          Dispatch_Type = PDispatch_Type
        END AND
        CASE WHEN PRecycler_ID = 0 OR PRecycler_ID IS NULL
          THEN
            1 = 1
        ELSE
          Recycler_ID = PRecycler_ID
        END AND
        CASE WHEN PLSP_ID = 0 OR PLSP_ID IS NULL
          THEN
            1 = 1
        ELSE
          LSP_ID = PLSP_ID
        END AND
        a.Category_ID = ifnull(PCategory_ID, a.Category_ID) AND
        b.Parent_Category_ID = ifnull(PParent_Category_ID, b.Parent_Category_ID) AND
        CASE WHEN POther_LSP_Name_Search = '' OR POther_LSP_Name_Search IS NULL
          THEN
            1 = 1
        ELSE
          Other_LSP LIKE concat('%', POther_LSP_Name_Search, '%')
        END
      ORDER BY oDispatch_Date DESC
      LIMIT _offset, PPage_Size;
    END IF;
  END;

DROP PROCEDURE IF EXISTS sDailyPlanRateGetList;
CREATE PROCEDURE sDailyPlanRateGetList
  (
    IN PEntity_ID       INT,
    IN PUser_ID         INT,
    IN PIs_Monthly_Rate INT,
    IN PNumber_Of_Days  INT,
    IN PType            VARCHAR(64)
  )
  BEGIN
    IF PIs_Monthly_Rate = 1
    THEN
      SELECT
        monthname(Dispatch_Date)           AS display,
        sum(ifnull(Tentative_Quantity, 0)) AS total_ten_qty,
        sum(ifnull(Quantity, 0))           AS total_qty
      FROM tDailyPlan
      WHERE Type = PType
      GROUP BY display;
    ELSE
      SELECT
        x.display,
        x.total_ten_qty,
        x.total_qty
      FROM (
             SELECT
               date_format(Dispatch_Date, '%d/%m/%Y') AS display,
               Dispatch_Date                          AS oDispacth,
               sum(ifnull(Tentative_Quantity, 0))     AS total_ten_qty,
               sum(ifnull(Quantity, 0))               AS total_qty
             FROM tDailyPlan
             WHERE Type = PType
             GROUP BY display
             ORDER BY oDispacth DESC
           ) x
      LIMIT 0, PNumber_Of_Days;
    END IF;
  END;

DROP PROCEDURE IF EXISTS sDataSyncUpdatesObjectGet;
CREATE PROCEDURE sDataSyncUpdatesObjectGet(IN PLast_Sync_Date VARCHAR(64) /* UTC Timestamp */)
  BEGIN

    /*
      1. Version Change
      2. Force Update
      3. State
      4. City
      5. Category
      6. Lookup
      7. Month
      8. Zone
      9. Activity
      10. Exercise
    */
    DECLARE _last_sync_time DATETIME;

    SET _last_sync_time = str_to_date(PLast_Sync_Date, '%d/%m/%Y %H:%i:%s');

    SELECT lpad(Update_Bit, 16, '0')
    FROM (
           SELECT bin(bit_or(conv(Update_Bit, 2, 10))) AS Update_Bit
           FROM tDataSyncUpdate
           WHERE Updated_On > _last_sync_time
         ) x;

  END;

DROP PROCEDURE IF EXISTS sDataSyncUpdatesAdd;
CREATE PROCEDURE sDataSyncUpdatesAdd(IN PObject_Type VARCHAR(1024))
  BEGIN

    /*
      1. Version Change
      2. Force Update
      3. State
      4. City
      5. Category
      6. Lookup
      7. Month
      8. Zone
      9. Activity
      10. Exercise
    */

    DECLARE _id INT;
    DECLARE _access_bit BIGINT;

    SET _access_bit =
    CASE WHEN lower(PObject_Type) = 'state'
      THEN 0010000000000000
    WHEN lower(PObject_Type) = 'city'
      THEN 0001000000000000
    WHEN lower(PObject_Type) = 'category'
      THEN 0000100000000000
    WHEN lower(PObject_Type) = 'lookup'
      THEN 0000010000000000
    WHEN lower(PObject_Type) = 'month'
      THEN 0000001000000000
    WHEN lower(PObject_Type) = 'zone'
      THEN 0000000100000000
    WHEN lower(PObject_Type) = 'activity'
      THEN 0000000010000000
    WHEN lower(PObject_Type) = 'exercise'
      THEN 0000000001000000
    ELSE
      0000000000000000
    END;

    IF _access_bit <> 0
    THEN
      INSERT INTO tDataSyncUpdate (Updated_On, Update_Bit) VALUES
        (utc_timestamp(), _access_bit);
      SET _id = (SELECT last_insert_id());
    END IF;

    CALL sGetTransactionStatus(1, _id, '_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDebitCreditNoteHeaderAdd;
CREATE PROCEDURE sDebitCreditNoteHeaderAdd
  (
    IN PEntity_ID        INT,
    IN PUser_ID          INT,
    IN PTransaction_Date VARCHAR(24),
    IN PNote_Type        VARCHAR(64), -- Lookup NOTEDEBIT, NOTECREDIT
    IN PTransaction_ID   INT,
    IN PTransaction_Type VARCHAR(64),
    IN PRemarks          VARCHAR(4048)
                         CHARACTER SET utf8
                         COLLATE utf8_unicode_ci
  )
  BEGIN
    DECLARE _id INT DEFAULT NULL;
    DECLARE _default_assignee INT;
    DECLARE _current_status INT;

    SET _default_assignee = (SELECT Configured_Value
                             FROM tConfig
                             WHERE Config_Item = 'DBCRNOTE_DEFAULT_ASSIGNEE'
                             LIMIT 0, 1);

    SET _current_status = (SELECT Status_ID
                           FROM tStatus
                           WHERE Transaction_Type = 'DBCRNOTE' AND Is_Default = 1
                           LIMIT 0, 1);

    INSERT INTO tDebitCreditNoteHeader
    (Created_By, Created_On, Transaction_Date, Note_Type, Transaction_ID, Transaction_Type, Remarks, Last_Updated_On, Last_Updated_By, Status_ID, Current_Assignee)
    VALUES
      (PUser_ID, now(), str_to_date(PTransaction_Date, '%d/%m/%Y'), PNote_Type, PTransaction_ID, PTransaction_Type,
                 PRemarks, now(), PUser_ID, _current_status, _default_assignee);

    SET _id = (SELECT last_insert_id());

    -- CALL sGetTransactionStatus(1, _id, 'Note_ID', NULL, NULL);

    SELECT
      1                 AS IS_SUCCESS,
      _id               AS TRANSACTION_ID,
      'Note_ID'         AS PRIMARY_COLUMN,
      NULL              AS DB_RESPONSE_CODE,
      NULL              AS DB_RESPONSE_MESSAGE,
      _default_assignee AS assigned_to,
      NULL              AS assigned_by
    FROM dual;

  END;

DROP PROCEDURE IF EXISTS sDebitCreditNoteHeaderCreatorUpdate;
CREATE PROCEDURE sDebitCreditNoteHeaderCreatorUpdate
  (
    IN PEntity_ID        INT,
    IN PUser_ID          INT,
    IN PNote_ID          INT,
    IN PTransaction_Date DATE,
    IN PNote_Type        VARCHAR(64), -- Lookup NOTEDEBIT, NOTECREDIT
    IN PRemarks          VARCHAR(4048)
                         CHARACTER SET utf8
                         COLLATE utf8_unicode_ci
  )
  BEGIN
    UPDATE tDebitCreditNoteHeader
    SET Transaction_Date = str_to_date(PTransaction_Date, '%d/%m/%Y'),
      Note_Type          = PNote_Type,
      Remarks            = PRemarks,
      Last_Updated_On    = now(),
      Last_Updated_By    = PUser_ID
    WHERE Note_ID = PNote_ID;

    CALL sGetTransactionStatus(1, PNote_ID, 'Note_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDebitCreditNoteHeaderUpdate;
CREATE PROCEDURE sDebitCreditNoteHeaderUpdate
  (
    IN PEntity_ID        INT,
    IN PUser_ID          INT,
    IN PNote_ID          INT,
    IN PStatus_ID        INT,
    IN PCurrent_Assignee INT,
    IN PVoucher_Date     VARCHAR(64),
    IN PVoucher_Number   VARCHAR(1024)
  )
  BEGIN
    UPDATE tDebitCreditNoteHeader
    SET
      Status_ID        = PStatus_ID,
      Current_Assignee = PCurrent_Assignee,
      Voucher_Date     = CASE WHEN PVoucher_Date = '' OR PVoucher_Date IS NULL
        THEN NULL
                         ELSE str_to_date(PVoucher_Date, '%d/%m/%Y') END,
      Voucher_Number   = PVoucher_Number,
      Last_Updated_On  = now(),
      Last_Updated_By  = PUser_ID
    WHERE Note_ID = PNote_ID;

    CALL sGetTransactionStatus(1, PNote_ID, 'Note_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDebitCreditNoteDetailAdd;
CREATE PROCEDURE sDebitCreditNoteDetailAdd
  (
    IN PNote_ID INT,
    IN PItem_ID INT, -- For Vendor and Recycler it is category for Logistic cost it may be item id
    IN PRate    FLOAT,
    IN PGST     FLOAT
  )
  BEGIN
    INSERT INTO tDebitCreditNoteDetail (Note_ID, Item_ID, Rate, GST) VALUES
      (PNote_ID, PItem_ID, PRate, PGST);

    CALL sGetTransactionStatus(1, PNote_ID, 'Note_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDebitCreditNoteDetailCreatorUpdate;
CREATE PROCEDURE sDebitCreditNoteDetailCreatorUpdate
  (
    IN PNote_ID INT,
    IN PItem_ID INT, -- For Vendor and Recycler it is category for Logistic cost it may be item id
    IN PRate    FLOAT,
    IN PGST     FLOAT
  )
  BEGIN
    UPDATE tDebitCreditNoteDetail
    SET Rate = PRate,
      GST    = PGST
    WHERE Note_ID = PNote_ID AND Item_ID = PItem_ID;

    CALL sGetTransactionStatus(1, PNote_ID, 'Note_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDebitCreditNoteDetailUpdate;
CREATE PROCEDURE sDebitCreditNoteDetailUpdate
  (
    IN PNote_ID       INT,
    IN PItem_ID       INT, -- For Vendor and Recycler it is category for Logistic cost it may be item id
    IN PApproved_Rate FLOAT,
    IN PApproved_GST  FLOAT
  )
  BEGIN
    UPDATE tDebitCreditNoteDetail
    SET Approved_Rate = PApproved_GST,
      Approved_GST    = PApproved_GST
    WHERE Note_ID = PNote_ID AND Item_ID = PItem_ID;

    CALL sGetTransactionStatus(1, PNote_ID, 'Note_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sDebitCreditNoteHistoryAdd;
CREATE PROCEDURE sDebitCreditNoteHistoryAdd
  (
    IN PNote_ID     INT,
    IN PAssigned_By INT,
    IN PAssigned_To INT,
    IN PComments    VARCHAR(4048)
                    CHARACTER SET utf8
                    COLLATE utf8_unicode_ci
  )
  BEGIN
    INSERT INTO tDebitCreditNoteHistory (Note_ID, Assigned_By, Assigned_To, Comments) VALUES
      (PNote_ID, PAssigned_By, PAssigned_To, PComments);

    CALL sGetTransactionStatus(1, PNote_ID, 'Note_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDebitCreditNoteHeaderObjectGet;
CREATE PROCEDURE sDebitCreditNoteHeaderObjectGet
  (
    IN PEntity_ID        INT,
    IN PUser_ID          INT,
    IN PTransactioN_ID   INT,
    IN PTransaction_Type VARCHAR(64)
  )
  BEGIN
    SELECT
      Note_ID,
      Created_By,
      Created_On,
      Transaction_Date,
      Note_Type,
      Transaction_ID,
      Transaction_Type,
      Remarks,
      Voucher_Number,
      Voucher_Date,
      Status_ID,
      Current_Assignee,
      Assigned_On,
      Last_Updated_On,
      Last_Updated_By
    FROM tDebitCreditNoteHeader
    WHERE
      Transaction_ID = PTransactioN_ID AND Transaction_Type = PTransaction_Type;
  END;

DROP PROCEDURE IF EXISTS sDebitCreditNoteDetailGetList;
CREATE PROCEDURE sDebitCreditNoteDetailGetList
  (
    IN PNote_ID INT
  )
  BEGIN
    SELECT
      _ID,
      Note_ID,
      Item_ID,
      Rate,
      GST,
      Approved_Rate,
      Approved_GST
    FROM tDebitCreditNoteDetail
    WHERE Note_ID = PNote_ID;
  END;


DROP PROCEDURE IF EXISTS sDebitCreditNoteHistoryGetList;
CREATE PROCEDURE sDebitCreditNoteHistoryGetList
  (
    IN PNote_ID INT
  )
  BEGIN
    SELECT
      _ID,
      Note_ID,
      Created_On,
      Assigned_By,
      Assigned_To,
      Comments
    FROM tDebitCreditNoteHistory
    WHERE Note_ID = PNote_ID;
  END;

/* Transaction Get List Page */
DROP PROCEDURE IF EXISTS sDebitCreditNoteHeaderObjectGetListPage;
CREATE PROCEDURE sDebitCreditNoteHeaderObjectGetListPage
  (
    IN PEntity_ID                 INT,
    IN PUser_ID                   INT,
    IN PFY                        INT,
    IN PFrom_Date                 VARCHAR(64),
    IN PTo_Date                   VARCHAR(64),
    IN PCreated_By                INT,
    IN PNote_Type                 VARCHAR(64),
    IN PTransaction_Type          VARCHAR(64),
    IN PStatus_IDs                VARCHAR(1024),
    IN PIs_Assigned_To_Me         INT,
    IN PIs_Including_Me           INT,
    IN PHierarchy_Type            VARCHAR(64),
    IN PInvoice_Number_Search_key VARCHAR(128),
    IN PCreated_By_Search_Key     VARCHAR(64),
    IN PPage_Num                  INT,
    IN PPage_Size                 INT
  )
  BEGIN

    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;
    DECLARE _from_date DATE;
    DECLARE _to_date DATE;

    SET _offset = fGetOffset(PPage_Num, PPage_Size);

    IF PFY = 0 OR PFY IS NULL
    THEN
      SET PFY = fGetFinacialYearByDate(curdate());
    END IF;

    IF PFrom_Date = '' OR PFrom_Date IS NULL
    THEN
      SET _from_date = NULL;
    ELSE
      SET _from_date = str_to_date(PFrom_Date, '%d/%m/%Y');
    END IF;

    IF PTo_Date = '' OR PTo_Date IS NULL
    THEN
      SET _to_date = NULL;
    ELSE
      SET _to_date = str_to_date(PTo_Date, '%d/%m/%Y');
    END IF;

    IF PCreated_By = 0
    THEN SET PCreated_By = NULL; END IF;
    IF PNote_Type = ''
    THEN SET PNote_Type = NULL; END IF;
    IF PTransaction_Type = ''
    THEN SET PTransaction_Type = NULL; END IF;


    SET _total_rec = (
      SELECT count(DISTINCT Note_ID)
      FROM tDebitCreditNoteHeader
      WHERE
        CASE WHEN PFY = 0 OR PFY IS NULL
          THEN
            1 = 1
        ELSE
          fGetFinacialYearByDate(date(Transaction_Date)) = PFY
        END AND
        CASE WHEN _from_date IS NULL
          THEN
            1 = 1
        ELSE
          DATE(Transaction_Date) >= _from_date
        END AND
        CASE WHEN _to_date IS NULL
          THEN
            1 = 1
        ELSE
          DATE(Transaction_Date) <= _from_date
        END AND
        CASE WHEN PStatus_IDs = '' OR PStatus_IDs IS NULL
          THEN
            1 = 1
        ELSE
          find_in_set(Status_ID, PStatus_IDs)
        END AND
        Note_Type = ifnull(PNote_Type, Note_Type) AND
        CASE WHEN PIs_Including_Me = 1
          THEN
            Note_ID IN (SELECT DISTINCT history.Note_ID
                        FROM tDebitCreditNoteHistory AS history
                        WHERE history.Assigned_By = PUser_ID OR history.Assigned_To = PUser_ID)
        ELSE
          CASE WHEN PIs_Assigned_To_Me = 1
            THEN
              Current_Assignee = PUser_ID
          ELSE
            1 = 1
          END
        END AND
        CASE WHEN PCreated_By = 0 OR PCreated_By IS NULL
          THEN
            CASE WHEN PCreated_By_Search_Key = '' OR PCreated_By_Search_Key IS NULL
              THEN
                1 = 1
            ELSE
              Created_By IN (SELECT x.Entity_ID
                             FROM tEntity x
                             WHERE x.Entity_Name LIKE concat('%', PCreated_By_Search_Key, '%'))
            END
        ELSE
          Created_By = PCreated_By
        END AND
        CASE WHEN PTransaction_Type = '' OR PTransaction_Type IS NULL
          THEN
            1 = 1
        ELSE
          Transaction_Type = PTransaction_Type AND
          CASE WHEN PInvoice_Number_Search_key = '' OR PInvoice_Number_Search_key IS NULL
            THEN
              1 = 1
          ELSE
            CASE WHEN PTransaction_Type = 'KAROINV'
              THEN
                Transaction_ID IN (SELECT y.Invoice_ID
                                   FROM tInvoiceHeader y
                                   WHERE y.Updated_Invoice_Number LIKE concat('%', PInvoice_Number_Search_key, '%'))
            ELSE
              Transaction_ID IN (SELECT z.Bill_ID
                                 FROM tSellerBill z
                                 WHERE z.Updated_Bill_Number LIKE concat('%', PInvoice_Number_Search_key, '%'))
            END
          END
        END
    );

    SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);

    IF _total_rec > 0
    THEN

      SELECT
        _total_rec   AS total_records,
        _total_pages AS total_pages;

      SELECT
        Note_ID,
        Created_By,
        Created_On,
        date_format(Transaction_Date, '%d/%m/%Y') AS Transaction_Date,
        Note_Type,
        Transaction_ID,
        Transaction_Type,
        Remarks,
        Voucher_Number,
        DATE_FORMAT(Voucher_Date, '%d/%m/%Y')     AS Voucher_Date,
        Status_ID,
        Current_Assignee,
        Assigned_On,
        Last_Updated_On,
        Last_Updated_By
      FROM tDebitCreditNoteHeader
      WHERE
        CASE WHEN PFY = 0 OR PFY IS NULL
          THEN
            1 = 1
        ELSE
          fGetFinacialYearByDate(date(Transaction_Date)) = PFY
        END AND
        CASE WHEN _from_date IS NULL
          THEN
            1 = 1
        ELSE
          DATE(Transaction_Date) >= _from_date
        END AND
        CASE WHEN _to_date IS NULL
          THEN
            1 = 1
        ELSE
          DATE(Transaction_Date) <= _from_date
        END AND
        CASE WHEN PStatus_IDs = '' OR PStatus_IDs IS NULL
          THEN
            1 = 1
        ELSE
          find_in_set(Status_ID, PStatus_IDs)
        END AND
        Note_Type = ifnull(PNote_Type, Note_Type) AND
        CASE WHEN PIs_Including_Me = 1
          THEN
            Note_ID IN (SELECT DISTINCT history.Note_ID
                        FROM tDebitCreditNoteHistory AS history
                        WHERE history.Assigned_By = PUser_ID OR history.Assigned_To = PUser_ID)
        ELSE
          CASE WHEN PIs_Assigned_To_Me = 1
            THEN
              Current_Assignee = PUser_ID
          ELSE
            1 = 1
          END
        END AND
        CASE WHEN PCreated_By = 0 OR PCreated_By IS NULL
          THEN
            CASE WHEN PCreated_By_Search_Key = '' OR PCreated_By_Search_Key IS NULL
              THEN
                1 = 1
            ELSE
              Created_By IN (SELECT x.Entity_ID
                             FROM tEntity x
                             WHERE x.Entity_Name LIKE concat('%', PCreated_By_Search_Key, '%'))
            END
        ELSE
          Created_By = PCreated_By
        END AND
        CASE WHEN PTransaction_Type = '' OR PTransaction_Type IS NULL
          THEN
            1 = 1
        ELSE
          Transaction_Type = PTransaction_Type AND
          CASE WHEN PInvoice_Number_Search_key = '' OR PInvoice_Number_Search_key IS NULL
            THEN
              1 = 1
          ELSE
            CASE WHEN PTransaction_Type = 'KAROINV'
              THEN
                Transaction_ID IN (SELECT y.Invoice_ID
                                   FROM tInvoiceHeader y
                                   WHERE y.Updated_Invoice_Number LIKE concat('%', PInvoice_Number_Search_key, '%'))
            ELSE
              Transaction_ID IN (SELECT z.Bill_ID
                                 FROM tSellerBill z
                                 WHERE z.Updated_Bill_Number LIKE concat('%', PInvoice_Number_Search_key, '%'))
            END
          END
        END
      ORDER BY Last_Updated_On DESC
      LIMIT _offset, PPage_Size;
    END IF;

  END;

DROP PROCEDURE IF EXISTS sDepartmentAdd;
CREATE PROCEDURE sDepartmentAdd(IN PEntiy_ID INT, IN PUser_ID INT,IN PDepartment_Name VARCHAR(264))
  BEGIN
    DECLARE _id INT;
    INSERT INTO tDepartment
    (Department_Name)
    VALUES
      (PDepartment_Name);

    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Department_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDepartmentUpdate;
CREATE PROCEDURE sDepartmentUpdate(IN PEntiy_ID INT,
                                        IN PUser_ID       INT,IN PDepartment_ID INT, IN PDepartment_Name VARCHAR(264))
  BEGIN
    DECLARE _id INT;
    UPDATE tDepartment
    SET
      Department_ID   = PDepartment_ID,
      Department_Name = PDepartment_Name
    WHERE Department_ID = PDepartment_ID;
    SET _id = PDepartment_ID;

    CALL sGetTransactionStatus(1, _id, 'Department_ID', NULL, NULL);

  END;

DROP PROCEDURE IF EXISTS sDepartmentDelete;
CREATE PROCEDURE sDepartmentDelete( IN PEntiy_ID INT, IN PUser_ID INT, IN PID INT)
  BEGIN
    UPDATE tDepartment
      SET Is_Delete = 1
    WHERE Department_ID = PID;
    CALL sGetTransactionStatus(1, PID, 'Department_ID', NULL, NULL);

  END;


DROP PROCEDURE IF EXISTS sDepartmentGet;
CREATE PROCEDURE sDepartmentGet(
  IN PID INT)
  BEGIN
    SELECT
      Department_ID,
      Department_Name
    FROM tDepartment
    WHERE Department_ID = PID;
  END;


DROP PROCEDURE IF EXISTS sDepartmentGetList;
CREATE PROCEDURE sDepartmentGetList(
  IN PIDs TEXT)
  BEGIN
    IF PIDs = '' OR PIDs IS NULL
    THEN
      SELECT
        Department_ID,
        Department_Name
      FROM tDepartment;

    ELSE
      SELECT
        Department_ID,
        Department_Name
      FROM tDepartment
      WHERE find_in_set(Department_ID, PIDs);
    END IF;

  END;



DROP PROCEDURE IF EXISTS sDispatchAdd;
CREATE PROCEDURE sDispatchAdd(IN PEntity_ID INT,IN PUser_ID INT, IN PLogistic_ID INT,  IN PTruck_Number VARCHAR(1024),
                                   IN PDriver_Name VARCHAR(1024), IN PDiver_Number VARCHAR(1024), IN PComments TEXT)
  BEGIN

    DECLARE _id INT;

    INSERT INTO tDispatch (Logistic_ID, Created_By, Truck_Number, Driver_Name, Diver_Number, Comments)
    VALUES
      (PLogistic_ID,PUser_ID  ,PTruck_Number, PDriver_Name, PDiver_Number, PComments);
    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Dispatch_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sDispatchUpdate;
CREATE PROCEDURE sDispatchUpdate(IN PInvoice_ID   INT,
                                 IN PTruck_Number VARCHAR(64),
                                 IN PDriver_Name  VARCHAR(64),
                                 IN PDiver_Number VARCHAR(64),
                                 IN PLogistic_ID  INT)
  BEGIN
    DECLARE _dispatch_id INT;
    SET _dispatch_id = (SELECT Dispatch_ID
                        FROM tInvoiceHeader
                        WHERE Invoice_ID = PInvoice_ID);
    UPDATE tDispatch
    SET Truck_Number = PTruck_Number,
      Driver_Name    = PDriver_Name,
      Diver_Number   = PDiver_Number,
      Logistic_ID    = PLogistic_ID
    WHERE Dispatch_ID = _dispatch_id;

    CALL sGetTransactionStatus(1,_dispatch_id, "Dispatch_ID",NULL ,NULL );

  END;

    DROP PROCEDURE IF EXISTS sDispatchGetList;
CREATE PROCEDURE sDispatchGetList(IN PDispatch_ID INT)
  BEGIN
    SELECT
      Dispatch_ID,
      date_format(Dispatched_On, '%d/%m/%Y %H:%m:%i') AS Dispatched_On,
      Logistic_ID,
      Created_By,
      Truck_Number,
      Driver_Name,
      Diver_Number,
      Comments
    FROM tDispatch
    WHERE Dispatch_ID = PDispatch_ID;
  END;
--
--
--
--
-- DROP PROCEDURE IF EXISTS sDispatchDelete;
-- CREATE PROCEDURE sDispatchDelete(IN PID INT)
--   BEGIN
--     DELETE FROM tDispatch
--     WHERE Dispatch_ID = PID;
--     CALL sGetTransactionStatus(1, PID, 'Dispatch_ID', NULL, NULL);
--
--   END;
--
--
-- DROP PROCEDURE IF EXISTS sDispatchGet;
-- CREATE PROCEDURE sDispatchGet(IN PID INT)
--   BEGIN
--     SELECT
--       Dispatch_ID, Dispatched_On, Logistic_ID, Created_By, Truck_Number, Driver_Name, Diver_Number, Comments
--     FROM tDispatch
--     WHERE Dispatch_ID = PID;
--   END;
--
-- DROP PROCEDURE IF EXISTS sDispatchGetList;
-- CREATE PROCEDURE sDispatchGetList(IN PIDs TEXT)
--   BEGIN
--     IF PIDs = '' OR PIDs IS NULL
--     THEN
--       SELECT
--       Dispatch_ID, Dispatched_On, Logistic_ID, Created_By, Truck_Number, Driver_Name, Diver_Number, Comments
--
--       FROM tDispatch;
--
--     ELSE
--       SELECT
--       Dispatch_ID, Dispatched_On, Logistic_ID, Created_By, Truck_Number, Driver_Name, Diver_Number, Comments
--
--       FROM tDispatch
--       WHERE find_in_set(Dispatch_ID, PIDs);
--     END IF;
--
--   END;
--
-- DROP PROCEDURE IF EXISTS sDispatchObjectGet;
-- CREATE PROCEDURE sDispatchObjectGet(IN PID INT)
--   BEGIN
--     SELECT
--       Dispatch_ID, Dispatched_On, Logistic_ID, Created_By, Truck_Number, Driver_Name, Diver_Number, Comments
--     FROM tDispatch
--     # WHERE Dispatch_ID = PDispatch_ID;
--   END;
--
--
--
-- DROP PROCEDURE IF EXISTS sDispatchObjectGetList;
-- CREATE PROCEDURE sDispatchObjectGetList(IN PDispatch_ID int(11), IN PDispatched_On varchar(24), IN PLogistic_ID int(11), IN PCreated_By int(11), IN PTruck_Number varchar(1024), IN PDriver_Name varchar(1024), IN PDiver_Number bigint(20), IN PComments text)
--   BEGIN
--
--
--     SELECT
--     Dispatch_ID, Dispatched_On, Logistic_ID, Created_By, Truck_Number, Driver_Name, Diver_Number, Comments
--
--     FROM
--       tDispatch
--     #       WHERE Status = PStatus
--     ORDER BY Dispatch_ID DESC;
--
--   END;
--
--
-- DROP PROCEDURE IF EXISTS sDispatchObjectGetListPage;
-- CREATE PROCEDURE sDispatchObjectGetListPage(IN PDispatch_ID int(11), IN PDispatched_On varchar(24), IN PLogistic_ID int(11), IN PCreated_By int(11), IN PTruck_Number varchar(1024), IN PDriver_Name varchar(1024), IN PDiver_Number bigint(20), IN PComments text , IN PPage_Num  INT, IN PPage_Size INT)
--   BEGIN
--
--     DECLARE _offset INT DEFAULT 0;
--     DECLARE _total_rec INT DEFAULT 0;
--     DECLARE _total_pages INT DEFAULT 1;
--     SET _offset = fGetOffset(PPage_Num, PPage_Size);
--     SET _total_rec = (SELECT count(*)
--                       FROM tDispatch
--       #       WHERE Status = PStatus
--
--     );
--     SET _total_pages = fGetTotalPages(_total_rec, PPage_Size);
--     IF _total_rec > 0
--     THEN
--
--       SELECT
--         _total_rec   AS total_records,
--         _total_pages AS total_pages;
--
--       SELECT
--       Dispatch_ID, Dispatched_On, Logistic_ID, Created_By, Truck_Number, Driver_Name, Diver_Number, Comments
--
--       FROM
--         tDispatch
--       #       WHERE Status = PStatus
--       ORDER BY Dispatch_ID DESC
--       LIMIT _offset, PPage_Size;
--
--     END IF;
--   END;
--
--


DROP PROCEDURE IF EXISTS sGetEmailReportDetailByID;
CREATE PROCEDURE sGetEmailReportDetailByID(IN PEmail_Report_ID INT)
  BEGIN
    SELECT
      Email_Report_ID,
      Report_Name,
      SP_Name,
      Type,
      XSLT_File_Name
    FROM tEmailReport a
    WHERE Email_Report_ID = PEmail_Report_ID;
  END;

DROP PROCEDURE IF EXISTS sGetEmailReports;
CREATE PROCEDURE sGetEmailReports()
  BEGIN
    SELECT
      Email_Report_ID,
      Report_Name,
      SP_Name,
      Type,
      XSLT_File_Name,
      CASE WHEN exists(SELECT *
                       FROM tEmailReportRecipients x
                       WHERE x.Email_Report_ID = a.Email_Report_ID)
        THEN
          (SELECT group_concat(y.Email_ID)
           FROM tEmailReportRecipients x INNER JOIN tEmailId y ON x.Email_ID = y.ID
           WHERE x.Email_Report_ID = a.Email_Report_ID)
      ELSE
        (SELECT group_concat(Email_ID)
         FROM tEmailId)
      END AS Recipients
    FROM tEmailReport a
    WHERE Is_Enabled = 1;
  END;


/* Email reports */

DROP PROCEDURE IF EXISTS _sGetRptMailerTarget_Vs_Collected;
CREATE PROCEDURE _sGetRptMailerTarget_Vs_Collected()
  BEGIN
    SELECT
      z.Category_Code                                                                   AS Category_Code,
      Category_Name                                                                     AS Category_Name,
      ifnull(cast(Round(Target, 2) AS DECIMAL(10, 2)), 0.00)                            AS Target,
      ifnull(cast(Round(Collection, 2) AS DECIMAL(10, 2)), 0.00)                        AS Collection,
      CASE
      WHEN
        ifnull(cast(Round(PercentageCollection, 2) AS DECIMAL(10, 2)), 0.00) > 100
        THEN 100
      ELSE
        ifnull(cast(Round(PercentageCollection, 2) AS DECIMAL(10, 2)), 0.00)
      END
                                                                                        AS PercentageCollectionNumber,
      concat(ifnull(cast(Round(PercentageCollection, 2) AS DECIMAL(10, 2)), 0.00), '%') AS PercentageCollection,
      CASE
      WHEN ifnull(cast(Round(Target - Collection, 2) AS DECIMAL(10, 2)), 0.00) < 0
        THEN 0
      ELSE
        ifnull(cast(Round(Target - Collection, 2) AS DECIMAL(10, 2)), 0.00)
      END
                                                                                        AS RemainingCollection,
      CASE
      WHEN ifnull(cast(Round((Target - Collection) * 100 / Target, 2) AS DECIMAL(10, 2)), 0.00) < 0.00
        THEN 0
      ELSE
        ifnull(cast(Round((Target - Collection) * 100 / Target, 2) AS DECIMAL(10, 2)), 0.00)
      END
                                                                                        AS PercentageRemainingCollectionNumber,
      CASE
      WHEN ifnull(cast(Round(Target - Collection, 2) AS DECIMAL(10, 2)), 0.00) < 0
        THEN concat(0.00, '%')
      ELSE
        concat(ifnull(cast(Round((Target - Collection) * 100 / Target, 2) AS DECIMAL(10, 2)), 0.00), '%')
      END
                                                                                        AS PercentageRemainingCollection,
      ifnull(cast(Round(AvgPrice, 2) AS DECIMAL(10, 2)), 0.00)                          AS AvgPrice,
      ifnull(cast(Round(AvgPrice * Collection, 2) AS DECIMAL(10, 2)), 0.00)             AS Total_Price,
      ifnull(cast(Round(Recycled, 2) AS DECIMAL(10, 2)), 0.00)                          AS Recycled,
      CASE
      WHEN
        ifnull(cast(Round(PercentageRecycled, 2) AS DECIMAL(10, 2)), 0.00) > 100
        THEN
          100
      ELSE
        ifnull(cast(Round(PercentageRecycled, 2) AS DECIMAL(10, 2)), 0.00)
      END
                                                                                        AS PercentageRecycledNumber,
      concat(ifnull(cast(Round(PercentageRecycled, 2) AS DECIMAL(10, 2)), 0.00), '%')   AS PercentageRecycled,
      ifnull(cast(Round(Allocated, 2) AS DECIMAL(10, 2)), 0.00)                         AS Allocated,
      CASE
      WHEN
        ifnull(cast(Round(PercentageAllocated, 2) AS DECIMAL(10, 2)), 0.00) > 100
        THEN 100
      ELSE
        ifnull(cast(Round(PercentageAllocated, 2) AS DECIMAL(10, 2)), 0.00)
      END
                                                                                        AS PercentageAllocatedNumber,
      concat(ifnull(cast(Round(PercentageAllocated, 2) AS DECIMAL(10, 2)), 0.00), '%')  AS PercentageAllocated,
      ifnull(cast(Round(AvgRate, 2) AS DECIMAL(10, 2)), 0.00)                           AS AvgRate,
      #       CASE
      #       WHEN ifnull(Round(Collection - Recycled, 2), 0.00) < 0.00
      #         THEN 0.00
      #       ELSE ifnull(cast(Round(Collection - Recycled, 2) AS DECIMAL(10, 2)), 0.00)
      #       END                                                                               AS WH_Stock,
      ifnull(cast(Round(Stock, 2) AS DECIMAL(10, 2)), 0.00)                             AS WH_Stock,
      CASE WHEN ifnull(Round(Collection - Allocated, 2), 0.00) < 0.00
        THEN 0.00
      ELSE
        ifnull(cast(Round(Collection - Allocated, 2) AS DECIMAL(10, 2)), 0.00) END      AS Un_Allocated
    FROM (
           SELECT
             d.Category_Code,
             b.Category_Name,
             b.Category_ID,
             sum(Target_Value) / 1000                        AS Target,
             CASE WHEN b.Category_ID = 55
               THEN
                 x.Collection + 8.87
             ELSE
               x.Collection
             END                                             AS Collection,
             CASE WHEN b.Category_ID = 55
               THEN
                 (x.Collection + 8.87) * 100 / (sum(Target_Value) / 1000)
             ELSE
               x.Collection * 100 / (sum(Target_Value) / 1000)
             END                                             AS PercentageCollection,
             x.AvgPrice                                         AvgPrice,
             inv.Recycled,
             inv.Recycled * 100 / (sum(Target_Value) / 1000) AS PercentageRecycled,
             x.Allocated,
             x.Allocated * 100 / (sum(Target_Value) / 1000)  AS PercentageAllocated,
             inv.AvgRate,
             x.AvgPrice - inv.AvgRate                        AS NetCost,
             wh_stock.Stock                                  AS Stock
           FROM tProducerTargetDetail a
             INNER JOIN tProducerTargetHeader c ON a.Producer_Target_ID = c.Producer_Target_ID
             INNER JOIN tCategory b ON a.Parameter = b.Category_Code
             INNER JOIN tCategory d ON b.Parent_Category_ID = d.Category_ID
             -- Collection
             LEFT JOIN (
                         SELECT
                           Category_ID,
                           sum(Collection) AS Collection,
                           sum(AvgPrice)   AS AvgPrice,
                           sum(Allocated)  AS Allocated
                         FROM (

                                SELECT
                                  b.Category_ID,
                                  sum(a.Quantity) / 1000                             AS Collection,
                                  sum(a.Quantity * d.Actual_Price) / sum(a.Quantity) AS AvgPrice,
                                  sum(ifnull(a.Quantity, 0)) / 1000                  AS Allocated
                                FROM tCarryForwardItems a
                                  INNER JOIN tCategory b ON a.Category_ID = b.Category_ID
                                  INNER JOIN tSellerBill c ON a.Bill_ID = c.Bill_ID
                                  INNER JOIN tCartItem d ON c.Cart_ID = d.Cart_ID AND a.Category_ID = d.Category_ID
                                WHERE a.FY = 2020
                                GROUP BY b.Category_ID
                                UNION ALL
                                SELECT
                                  b.Category_ID,
                                  sum(f.Inward_Quantity) / 1000                                    AS Collection,
                                  sum(f.Inward_Quantity * b.Actual_Price) / sum(f.Inward_Quantity) AS AvgPrice,
                                  sum(ifnull(n.Quantity, 0)) / 1000                                AS Allocated
                                FROM tCart a
                                  INNER JOIN tCartItem b ON a.Cart_ID = b.Cart_ID
                                  INNER JOIN tPickupRequest c ON a.Cart_ID = c.Cart_ID
                                  INNER JOIN tItemHandover d ON c.Cart_ID = d.Cart_ID
                                  INNER JOIN tWHGRNHeader e ON d.Handover_ID = e.Handover_ID
                                  INNER JOIN tWHGRNDetail f
                                    ON e.WH_GRN_ID = f.WH_GRN_ID AND
                                       b.Category_ID = f.Category_ID
                                  INNER JOIN tEntity g ON a.Entity_ID = g.Entity_ID
                                  INNER JOIN tCity h ON g.City_ID = h.City_ID
                                  INNER JOIN tState i ON g.State_ID = i.State_ID
                                  INNER JOIN tCategory j ON b.Category_ID = j.Category_ID
                                  INNER JOIN tCategory k ON j.Parent_Category_ID = k.Category_ID
                                  INNER JOIN tWarehouse l ON c.WH_ID = l.WH_ID
                                  LEFT JOIN tSellerBill m ON a.Cart_ID = m.Cart_ID
                                  LEFT JOIN tCartItemAllocation n ON b.Cart_Item_ID = n.Cart_Item_ID
                                WHERE
                                  date(e.Transaction_Date) >= '2020-04-01'
                                #                                   c.Finacial_Year = 2020
                                GROUP BY b.Category_ID
                              ) y
                         GROUP BY Category_ID
                       ) x ON b.Category_ID = x.Category_ID
             -- Recycling
             LEFT JOIN (
                         SELECT
                           b.Category_ID,
                           sum(b.Quantity) / 1000                     AS Recycled,
                           sum(b.Quantity * b.Rate) / sum(b.Quantity) AS AvgRate
                         FROM tInvoiceHeader a
                           INNER JOIN tInvoiceDetail b ON a.Invoice_ID = b.Invoice_ID
                         WHERE Invoice_Date >= '2020-04-01'
                         GROUP BY b.Category_ID
                       ) inv ON b.Category_ID = inv.Category_ID
             -- WH Stock
             LEFT JOIN (
                         SELECT
                           Category_ID,
                           sum(Closing_Quantity) / 1000 AS Stock
                         FROM tWHStockTotal
                         GROUP BY Category_ID
                       ) wh_stock ON b.Category_ID = wh_stock.Category_ID
           WHERE c.Target_Year = 2020
           GROUP BY d.Category_ID, d.Category_Code, b.Category_Name, b.Category_ID
           ORDER BY d.Category_ID, 4 DESC
         ) z
      LEFT JOIN tCatSort y ON z.Category_Code = y.Category_Code
    ORDER BY y.SortNum, z.Category_Name;
  END;


/* Collection Zonewise */
DROP PROCEDURE IF EXISTS _sGetRptMailer_InwardCollectionZoneWise;
CREATE PROCEDURE _sGetRptMailer_InwardCollectionZoneWise()
  BEGIN
    SELECT
      'Daily'                               AS Type,
      ifnull(fGetCollectionByZone(1, 1), 0) AS South_West,
      ifnull(fGetCollectionByZone(2, 1), 0) AS North_East,
      0                                     AS North_West,
      0                                     AS South_East
    FROM dual
    UNION
    SELECT
      'Weekly'                              AS Type,
      ifnull(fGetCollectionByZone(1, 2), 0) AS South_West,
      ifnull(fGetCollectionByZone(2, 2), 0) AS North_East,
      0                                     AS North_West,
      0                                     AS South_East
    FROM dual
    UNION
    SELECT
      'Monthly'                             AS Type,
      ifnull(fGetCollectionByZone(1, 3), 0) AS South_West,
      ifnull(fGetCollectionByZone(2, 3), 0) AS North_East,
      0                                     AS North_West,
      0                                     AS South_East
    FROM dual;
  END;


DROP FUNCTION IF EXISTS fGetCollectionByZone;
CREATE FUNCTION fGetCollectionByZone(PZone_ID INT, PType INT)
  RETURNS FLOAT
  BEGIN
    RETURN (

      SELECT Round(sum(main.Quantity2), 2)
      FROM (
             SELECT Round(sum(a.Quantity), 2) AS Quantity2
             FROM tCarryForwardItems a
               INNER JOIN tSellerBill b ON a.Bill_ID = b.Bill_ID
               INNER JOIN tPickupRequest c ON b.Cart_ID = c.Cart_ID
               INNER JOIN tState d ON c.State_ID = d.State_ID
               INNER JOIN tZone e ON d.Zone_ID = e.Zone_ID
             WHERE a.FY = fGetFinacialYearByDate(curdate()) AND
                   CASE WHEN PType = 3
                     THEN
                       MONTH(b.Bill_Date) = MONTH(CURDATE())
                   ELSE
                     1 = 1
                   END
                   AND
                   CASE WHEN PType = 1
                     THEN
                       b.Bill_Date = subdate(CURDATE(), 1)
                   ELSE
                     1 = 1
                   END AND
                   CASE WHEN PType = 2
                     THEN
                       WEEK(DATE(b.Bill_Date)) = WEEK(CURDATE())
                   ELSE
                     1 = 1
                   END
                   AND e.Zone_ID = PZone_ID
             UNION ALL
             SELECT Round(sum(f.Inward_Quantity), 2) AS Quantity2
             FROM tCart a
               INNER JOIN tCartItem b ON a.Cart_ID = b.Cart_ID
               INNER JOIN tPickupRequest c ON a.Cart_ID = c.Cart_ID
               INNER JOIN tItemHandover d ON c.Cart_ID = d.Cart_ID
               INNER JOIN tWHGRNHeader e ON d.Handover_ID = e.Handover_ID
               INNER JOIN tWHGRNDetail f ON e.WH_GRN_ID = f.WH_GRN_ID AND b.Category_ID = f.Category_ID
               INNER JOIN tEntity g ON a.Entity_ID = g.Entity_ID
               INNER JOIN tCity h ON g.City_ID = h.City_ID
               INNER JOIN tState i ON g.State_ID = i.State_ID
               INNER JOIN tZone j ON i.Zone_ID = j.Zone_ID
               LEFT JOIN tSellerBill k ON a.Cart_ID = k.Cart_ID
             WHERE
               c.Finacial_Year = fGetFinacialYearByDate(CURDATE())
               AND
               CASE WHEN PType = 3
                 THEN
                   MONTH(e.Transaction_Date) = MONTH(CURDATE())
               ELSE
                 1 = 1
               END
               AND
               CASE WHEN PType = 1
                 THEN
                   e.Transaction_Date = subdate(CURDATE(), 1)
               ELSE
                 1 = 1
               END AND
               CASE WHEN PType = 2
                 THEN
                   WEEK(DATE(e.Transaction_Date)) = WEEK(CURDATE())
               ELSE
                 1 = 1
               END
               AND j.Zone_ID = PZone_ID
           ) main
    );
  END;


/* Avg price */
DROP PROCEDURE IF EXISTS _sGetRptMailer_AvgPrice;
CREATE PROCEDURE _sGetRptMailer_AvgPrice()
  BEGIN
    SELECT
      m.Category_Code,
      m.Category_Name,
      sum(Collection_cur)          AS Collection_cur,
      round(avg(AvgPrice_cur), 2)  AS AvgPrice_cur,
      sum(Total_Price_cur)         AS Total_Price_cur,
      sum(Collection_prev)         AS Collection_prev,
      round(avg(AvgPrice_prev), 2) AS AvgPrice_prev,
      sum(Total_Price_prev)        AS Total_Price_prev
    FROM (
           SELECT
             z.Category_Code                                                                AS Category_Code,
             Category_Name                                                                  AS Category_Name,
             ifnull(cast(Round(Collection, 2) AS DECIMAL(10, 2)), 0.00)                     AS Collection_cur,
             ifnull(cast(Round(AvgPrice, 2) AS DECIMAL(10, 2)), 0.00)                       AS AvgPrice_cur,
             ifnull(cast(Round(AvgPrice * (Collection * 1000), 2) AS DECIMAL(10, 2)), 0.00) AS Total_Price_cur,
             0                                                                              AS Collection_prev,
             0                                                                              AS AvgPrice_prev,
             0                                                                              AS Total_Price_prev
           FROM (
                  SELECT
                    d.Category_Code,
                    b.Category_Name,
                    b.Category_ID,
                    sum(Target_Value) / 1000                        AS Target,
                    -- coming from producer target
                    x.Collection                                    AS Collection,
                    x.Collection * 100 / (sum(Target_Value) / 1000) AS PercentageCollection,
                    x.AvgPrice                                         AvgPrice,
                    inv.Recycled,
                    inv.Recycled * 100 / (sum(Target_Value) / 1000) AS PercentageRecycled,
                    inv.AvgRate,
                    x.AvgPrice - inv.AvgRate                        AS NetCost
                  FROM tProducerTargetDetail a
                    INNER JOIN tProducerTargetHeader c ON a.Producer_Target_ID = c.Producer_Target_ID
                    INNER JOIN tCategory b ON a.Parameter = b.Category_Code
                    INNER JOIN tCategory d ON b.Parent_Category_ID = d.Category_ID

                    LEFT JOIN (
                                SELECT
                                  Category_ID,
                                  sum(Collection) AS Collection,
                                  avg(AvgPrice)   AS AvgPrice
                                FROM (
                                       SELECT
                                         b.Category_ID,
                                         sum(a.Quantity) / 1000 AS Collection,
                                         avg(d.Actual_Price)    AS AvgPrice
                                       #                                          sum(a.Quantity * d.Actual_Price) / sum(a.Quantity) / 1000 AS AvgPrice
                                       FROM tCarryForwardItems a
                                         INNER JOIN tCategory b ON a.Category_ID = b.Category_ID
                                         INNER JOIN tSellerBill c ON a.Bill_ID = c.Bill_ID
                                         INNER JOIN tCartItem d
                                           ON c.Cart_ID = d.Cart_ID AND a.Category_ID = d.Category_ID
                                       WHERE a.FY = 2020
                                       GROUP BY b.Category_ID
                                       UNION ALL
                                       SELECT
                                         b.Category_ID,
                                         sum(b.Inspected_Quantity) / 1000 AS Collection,
                                         avg(b.Actual_Price)              AS AvgPrice
                                       #                                          sum(b.Inspected_Quantity * b.Actual_Price) / sum(b.Inspected_Quantity) /
                                       #                                          1000                             AS AvgPrice
                                       FROM tCart a
                                         INNER JOIN tCartItem b ON a.Cart_ID = b.Cart_ID
                                         INNER JOIN tPickupRequest c ON a.Cart_ID = c.Cart_ID
                                         INNER JOIN tItemHandover d ON c.Cart_ID = d.Cart_ID
                                         INNER JOIN tWHGRNHeader e ON d.Handover_ID = e.Handover_ID
                                         INNER JOIN tWHGRNDetail f
                                           ON e.WH_GRN_ID = f.WH_GRN_ID AND
                                              b.Category_ID = f.Category_ID
                                         INNER JOIN tEntity g ON a.Entity_ID = g.Entity_ID
                                         INNER JOIN tCity h ON g.City_ID = h.City_ID
                                         INNER JOIN tState i ON g.State_ID = i.State_ID
                                         INNER JOIN tCategory j ON b.Category_ID = j.Category_ID
                                         INNER JOIN tCategory k ON j.Parent_Category_ID = k.Category_ID
                                         INNER JOIN tWarehouse l ON c.WH_ID = l.WH_ID
                                         LEFT JOIN tSellerBill m ON a.Cart_ID = m.Cart_ID
                                       WHERE
                                         date(e.Transaction_Date) > '2020-04-01'
                                       GROUP BY b.Category_ID
                                     ) y
                                GROUP BY Category_ID


                              ) x ON b.Category_ID = x.Category_ID
                    -- collection
                    LEFT JOIN (
                                SELECT
                                  b.Category_ID,
                                  sum(b.Quantity) / 1000                     AS Recycled,
                                  sum(b.Quantity * b.Rate) / sum(b.Quantity) AS AvgRate
                                FROM tInvoiceHeader a
                                  INNER JOIN tInvoiceDetail b ON a.Invoice_ID = b.Invoice_ID
                                WHERE Invoice_Date > '2020-03-31' AND Invoice_Date <= '2021-03-31'
                                GROUP BY b.Category_ID
                              ) inv ON b.Category_ID = inv.Category_ID
                  WHERE c.Target_Year = 2020
                  GROUP BY d.Category_ID, d.Category_Code, b.Category_Name, b.Category_ID
                  ORDER BY d.Category_ID, 4 DESC


                ) z

           UNION

           SELECT
             y.Category_Code                                                                AS Category_Code,
             Category_Name                                                                  AS Category_Name,
             0                                                                              AS Collection_cur,
             0                                                                              AS AvgPrice_cur,
             0                                                                              AS Total_Price_cur,
             ifnull(cast(Round(Collection, 2) AS DECIMAL(10, 2)), 0.00)                     AS Collection_prev,
             ifnull(cast(Round(AvgPrice, 2) AS DECIMAL(10, 2)), 0.00)                       AS AvgPrice_prev,
             ifnull(cast(Round(AvgPrice * (Collection * 1000), 2) AS DECIMAL(10, 2)), 0.00) AS Total_Price_prev
           FROM (
                  SELECT
                    d.Category_Code,
                    b.Category_Name,
                    b.Category_ID,
                    sum(Target_Value) / 1000                        AS Target,
                    -- coming from producer target
                    x.Collection                                    AS Collection,
                    x.Collection * 100 / (sum(Target_Value) / 1000) AS PercentageCollection,
                    x.AvgPrice                                         AvgPrice,
                    inv.Recycled,
                    inv.Recycled * 100 / (sum(Target_Value) / 1000) AS PercentageRecycled,
                    inv.AvgRate,
                    x.AvgPrice - inv.AvgRate                        AS NetCost
                  FROM tProducerTargetDetail a
                    INNER JOIN tProducerTargetHeader c ON a.Producer_Target_ID = c.Producer_Target_ID
                    INNER JOIN tCategory b ON a.Parameter = b.Category_Code
                    INNER JOIN tCategory d ON b.Parent_Category_ID = d.Category_ID

                    LEFT JOIN (
                                SELECT
                                  Category_ID,
                                  sum(Collection) AS Collection,
                                  avg(AvgPrice)   AS AvgPrice
                                FROM (
                                       SELECT
                                         b.Category_ID,
                                         sum(b.Inspected_Quantity) / 1000 AS Collection,
                                         avg(b.Actual_Price)              AS AvgPrice
                                       #                                          sum(b.Inspected_Quantity * b.Actual_Price) / sum(
                                       #                                              b.Inspected_Quantity)        AS AvgPrice
                                       FROM tCart a
                                         INNER JOIN tCartItem b ON a.Cart_ID = b.Cart_ID
                                         INNER JOIN tPickupRequest c ON a.Cart_ID = c.Cart_ID
                                         INNER JOIN tItemHandover d ON c.Cart_ID = d.Cart_ID
                                         INNER JOIN tWHGRNHeader e ON d.Handover_ID = e.Handover_ID
                                         INNER JOIN tWHGRNDetail f
                                           ON e.WH_GRN_ID = f.WH_GRN_ID AND
                                              b.Category_ID = f.Category_ID
                                         INNER JOIN tEntity g ON a.Entity_ID = g.Entity_ID
                                         INNER JOIN tCity h ON g.City_ID = h.City_ID
                                         INNER JOIN tState i ON g.State_ID = i.State_ID
                                         INNER JOIN tCategory j ON b.Category_ID = j.Category_ID
                                         INNER JOIN tCategory k ON j.Parent_Category_ID = k.Category_ID
                                         INNER JOIN tWarehouse l ON c.WH_ID = l.WH_ID
                                         LEFT JOIN tSellerBill m ON a.Cart_ID = m.Cart_ID
                                       WHERE
                                         m.Bill_Date > '2019-03-31' AND m.Bill_Date < '2020-03-31'
                                         OR
                                         (a.`Cart_ID` IN (SELECT cart_id
                                                          FROM
                                                            tBookedItem19))
                                       GROUP BY b.Category_ID
                                       UNION
                                       SELECT
                                         cat_id,
                                         sum(stock) / 1000,
                                         0
                                       FROM tStock31stMarch19
                                       GROUP BY cat_id) y
                                GROUP BY Category_ID


                              ) x ON b.Category_ID = x.Category_ID
                    -- collection
                    LEFT JOIN (
                                SELECT
                                  b.Category_ID,
                                  sum(b.Quantity) / 1000                     AS Recycled,
                                  sum(b.Quantity * b.Rate) / sum(b.Quantity) AS AvgRate
                                FROM tInvoiceHeader a
                                  INNER JOIN tInvoiceDetail b ON a.Invoice_ID = b.Invoice_ID
                                WHERE Invoice_Date > '2019-03-31' AND Invoice_Date <= '2020-03-31'
                                GROUP BY b.Category_ID
                              ) inv ON b.Category_ID = inv.Category_ID
                  WHERE c.Target_Year = 2019
                  GROUP BY d.Category_ID, d.Category_Code, b.Category_Name, b.Category_ID
                  ORDER BY d.Category_ID, 4 DESC
                ) y)
         m
      LEFT JOIN tCatSort n ON m.Category_Code = n.Category_Code
    GROUP BY m.Category_Name, m.Category_Code
    ORDER BY n.SortNum, m.Category_Name;

  END;

/* Stock On Hand */
DROP PROCEDURE IF EXISTS _sGetStockOnHand;
CREATE PROCEDURE _sGetStockOnHand()
  BEGIN
    SELECT
      a.WH_ID,
      State_Name,
      City_Name,
      a.Category_ID,
      f.Category_Code,
      e.Category_Name,
      Round(Closing_Quantity/1000, 2) AS Quantity
    FROM tWHStockTotal a
      INNER JOIN tWarehouse b ON a.WH_ID = b.WH_ID
      INNER JOIN tState c ON b.State_ID = c.State_ID
      INNER JOIN tCity d ON b.City_ID = d.City_ID
      INNER JOIN tCategory e ON a.Category_ID = e.Category_ID
      INNER JOIN tCategory f ON f.Category_ID = e.Parent_Category_ID
    WHERE Closing_Quantity > 0 AND a.WH_ID <> 5
    ORDER BY Quantity DESC, Warehouse_Name;
  END;

/* Collection for 7 days invoice wise */
DROP PROCEDURE IF EXISTS _sGetRptMailer_CollectionFor7DaysInvoiceWise;
CREATE PROCEDURE _sGetRptMailer_CollectionFor7DaysInvoiceWise()
  BEGIN

    SELECT
      DATE(Bill_Date)                  AS Bill_Date,
      f.Entity_Name,
      Updated_Bill_Number              AS Bill_Number,
      State_Name,
      City_Name,
      Round(sum(e.Inward_Quantity), 2) AS Quantity_Collected
    FROM tSellerBill a
      INNER JOIN tCart b ON a.Cart_ID = b.Cart_ID
      INNER JOIN tItemHandover c ON b.Cart_ID = c.Cart_ID
      INNER JOIN tWHGRNHeader d ON c.Handover_ID = d.Handover_ID
      INNER JOIN tWHGRNDetail e ON d.WH_GRN_ID = e.WH_GRN_ID
      INNER JOIN tEntity f ON b.Entity_ID = f.Entity_ID
      INNER JOIN tCity g ON f.City_ID = g.City_ID
      INNER JOIN tState h ON f.State_ID = h.State_ID
    WHERE Date(d.Transaction_Date) > subdate(CURDATE(), 7) AND date(d.Transaction_Date) <> CURDATE()
    GROUP BY f.Entity_Name, Updated_Bill_Number
    ORDER BY Bill_Date DESC, Bill_ID DESC;
  END;

DROP PROCEDURE IF EXISTS _sGetRptMailer_CollectionFor7Days;
CREATE PROCEDURE _sGetRptMailer_CollectionFor7Days()
  BEGIN

    SELECT
      DATE(Bill_Date)                  AS Bill_Date,
      ROUND(sum(e.Inward_Quantity), 2) AS Quantity_Collected
    FROM tSellerBill a
      INNER JOIN tCart b ON a.Cart_ID = b.Cart_ID
      INNER JOIN tItemHandover c ON b.Cart_ID = c.Cart_ID
      INNER JOIN tWHGRNHeader d ON c.Handover_ID = d.Handover_ID
      INNER JOIN tWHGRNDetail e ON d.WH_GRN_ID = e.WH_GRN_ID
    WHERE Date(d.Transaction_Date) > subdate(CURDATE(), 7) AND date(d.Transaction_Date) <> CURDATE()
    GROUP BY Bill_Date
    ORDER BY Bill_Date DESC, Bill_ID DESC;
  END;


DROP PROCEDURE IF EXISTS _sGetStockOnHandTotal;
CREATE PROCEDURE _sGetStockOnHandTotal()
  BEGIN
    SELECT
      a.WH_ID,
      b.Warehouse_Name,
      c.State_Name,
      d.City_Name,
      round(sum(Closing_Quantity)/1000,2) AS Total
    FROM tWHStockTotal a
      INNER JOIN tWarehouse b ON a.WH_ID = b.WH_ID
      INNER JOIN tState c ON b.State_ID = c.State_ID
      INNER JOIN tCity d ON b.City_ID = d.City_ID
    GROUP BY a.WH_ID
    ORDER BY Total DESC;
  END;

DROP PROCEDURE IF EXISTS sEntityAdd;
CREATE PROCEDURE sEntityAdd
  (
    IN PEntity_ID                 INT,
    IN PUser_ID                   INT,
    IN PEntity_Name               VARCHAR(1024),
    IN PDisplay_Name              VARCHAR(1024),
    IN PEntity_Type               VARCHAR(64),
    IN PAddress1                  VARCHAR(1024),
    IN PAddress2                  VARCHAR(1024),
    IN PAddress3                  VARCHAR(1024),
    IN PState_ID                  INT,
    IN PCity_ID                   INT,
    IN PPincode                   BIGINT,
    IN PMobile_Number             TEXT,
    IN PLandline_Number           TEXT,
    IN PEmail                     VARCHAR(1024),
    IN PManaged_By                INT,
    IN PParent_ID                 INT,
    IN PCurrently_Managed_By_User INT,
    IN PEnrollment_Status         VARCHAR(1024),
    IN PGST_No                    VARCHAR(264),
    IN PShip_Address1             VARCHAR(1024),
    IN PShip_Address2             VARCHAR(1024),
    IN PShip_Address3             VARCHAR(1024),
    IN PShip_Pincode              INT,
    IN PShip_State_ID             INT,
    IN PShip_City_ID              INT
  )
  BEGIN
    DECLARE _id INT;
    INSERT INTO tEntity (
      Entity_Name,
      Display_Name,
      Entity_Type,
      Address1,
      Address2,
      Address3,
      State_ID,
      City_ID,
      Pincode,
      Mobile_Number,
      Landline_Number,
      Email,
      Managed_By,
      Parent_Entity_ID,
      Created_By,
      Created_On,
      Currently_Managed_By,
      Is_Delete,
      Enrollment_Status,
      Entity_Status,
      Is_System,
      GST_No,
      Ship_Address1,
      Ship_Address2,
      Ship_Address3,
      Ship_Pincode,
      Ship_State_ID,
      Ship_City_ID,
      Last_Updated_On,
      Last_Updated_By
    )
    VALUES
      (
        PEntity_Name,
        PDisplay_Name,
        PEntity_Type,
        PAddress1,
        PAddress2,
        PAddress3,
        PState_ID,
        PCity_ID,
        PPincode,
        PMobile_Number,
        PLandline_Number,
        PEmail,
        PManaged_By,
        PParent_ID,
        PUser_ID,
        now(),
        PCurrently_Managed_By_User,
        0,
        PEnrollment_Status,
        'ACTIVE',
        0,
        PGST_No,
        PShip_Address1,
        PShip_Address2,
        PShip_Address3,
        PShip_Pincode,
        PShip_State_ID,
        PShip_City_ID,
        now(),
        PUser_ID
      );

    SET _id = (SELECT last_insert_id());

    CALL sGetTransactionStatus(1, _id, 'Entity_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sEntityUpdate;
CREATE PROCEDURE sEntityUpdate
  (
    IN PEntity_ID                 INT,
    IN PUser_ID                   INT,
    IN PUpdate_Entity_ID          INT,
    IN PEntity_Name               VARCHAR(1024),
    IN PDisplay_Name              VARCHAR(1024),
    IN PEntity_Type               VARCHAR(64),
    IN PAddress1                  VARCHAR(1024),
    IN PAddress2                  VARCHAR(1024),
    IN PAddress3                  VARCHAR(1024),
    IN PState_ID                  INT,
    IN PCity_ID                   INT,
    IN PPincode                   BIGINT,
    IN PMobile_Number             TEXT,
    IN PLandline_Number           TEXT,
    IN PEmail                     VARCHAR(1024),
    IN PManaged_By                INT,
    IN PParent_ID                 INT,
    IN PCurrently_Managed_By_User INT,
    IN PEnrollment_Status         VARCHAR(1024),
    IN PStatus                    VARCHAR(64),
    IN PGST_No                    VARCHAR(264),
    IN PShip_Address1             VARCHAR(1024),
    IN PShip_Address2             VARCHAR(1024),
    IN PShip_Address3             VARCHAR(1024),
    IN PShip_Pincode              INT,
    IN PShip_State_ID             INT,
    IN PShip_City_ID              INT
  )
  BEGIN
    UPDATE tEntity
    SET
      Entity_Name          = PEntity_Name,
      Display_Name         = PDisplay_Name,
      Entity_Type          = PEntity_Type,
      Address1             = PAddress1,
      Address2             = PAddress2,
      Address3             = PAddress3,
      State_ID             = PState_ID,
      City_ID              = PCity_ID,
      Pincode              = PPincode,
      Mobile_Number        = PMobile_Number,
      Landline_Number      = PLandline_Number,
      Email                = PEmail,
      Managed_By           = PManaged_By,
      Parent_Entity_ID     = PParent_ID,
      Currently_Managed_By = PCurrently_Managed_By_User,
      Enrollment_Status    = PEnrollment_Status,
      Entity_Status        = PStatus,
      GST_No               = PGST_No,
      Ship_Address1        = PShip_Address1,
      Ship_Address2        = PShip_Address2,
      Ship_Address3        = PShip_Address3,
      Ship_Pincode         = PShip_Pincode,
      Ship_State_ID        = PShip_State_ID,
      Ship_City_ID         = PShip_City_ID,
      Last_Updated_On      = NOW(),
      Last_Updated_By      = PUser_ID
    WHERE Entity_ID = PUpdate_Entity_ID;

    CALL sGetTransactionStatus(1, PUpdate_Entity_ID, 'Entity_ID', NULL, NULL);
  END;

DROP PROCEDURE IF EXISTS sEntityDelete;
CREATE PROCEDURE sEntityDelete
  (
    IN PEntity_ID        INT,
    IN PUser_ID          INT,
    IN PDelete_Entity_ID INT
  )
  BEGIN
    UPDATE tEntity
    SET
      Entity_Status = 'DEACTIVE',
      Is_Delete     = 1
    WHERE Entity_ID = PDelete_Entity_ID;

    UPDATE tUser a
      INNER JOIN tUserEntity b ON a.User_ID = b.User_ID
    SET a.Is_Delete = 1, a.Status = 'DEACTIVE'
    WHERE b.Entity_ID = PDelete_Entity_ID;

    CALL sGetTransactionStatus(1, PDelete_Entity_ID, 'Entity_ID', NULL, NULL);
  END;


DROP PROCEDURE IF EXISTS sEntitiesGetList;
CREATE PROCEDURE sEntitiesGetList(IN PEntity_IDs TEXT)
  BEGIN
    SELECT
      Entity_ID,
      Entity_Name,
      Display_Name,
      Entity_Type,
      Address1,
      Address2,
      Address3,
      State_ID,
      City_ID,
      Pincode,
      Mobile_Number,
      Landline_Number,
      Email,
      Photo,
      Managed_By,
      Parent_Entity_ID,
      Created_By,
      Created_On,
      Currently_Managed_By,
      Is_Delete,
      Enrollment_Status,
      Entity_Status,
      NULL AS Gov_Body_Type,
      Latitude_Longitude,
      Signature,
      GST_No,
      Ship_Address1,
      Ship_Address2,
      Ship_Address3,
      Ship_Pincode,
      Ship_State_ID,
      Ship_City_ID,
      Is_System,
      Last_Updated_On
    FROM tEntity
    WHERE find_in_set(Entity_ID, PEntity_IDs);
  END;


DROP PROCEDURE IF EXISTS sEntityGet;
CREATE PROCEDURE sEntityGet(IN PEntity_ID INT)
  BEGIN
    SELECT
      Entity_ID,
      Entity_Name,
      Display_Name,
      Entity_Type,
      Address1,
      Address2,
      Address3,
      State_ID,
      City_ID,
      Pincode,
      Mobile_Number,
      Landline_Number,
      Email,
      Photo,
      Managed_By,
      Parent_Entity_ID,
      Created_By,
      Created_On,
      Currently_Managed_By,
      Is_Delete,
      Enrollment_Status,
      Entity_Status,
      NULL AS Gov_Body_Type,
      Latitude_Longitude,
      Signature,
      GST_No,
      Ship_Address1,
      Ship_Address2,
      Ship_Address3,
      Ship_Pincode,
      Ship_State_ID,
      Ship_City_ID,
      Is_System,
      Last_Updated_On
    FROM tEntity
    WHERE Entity_ID = PEntity_ID;
  END;


DROP PROCEDURE IF EXISTS sMyEntitiesGetList;
CREATE PROCEDURE sMyEntitiesGetList
  (
    IN PEntity_ID              INT,
    IN PUser_ID                INT,
    IN PEntity_Type            VARCHAR(1024),
    IN PState_ID               INT,
    IN PCity_ID                INT,
    IN PEntity_Name_Search_Key TEXT,
    IN PPage_Num               INT,
    IN PNum_Rec                INT
  )
  BEGIN
    DECLARE _offset INT DEFAULT 0;
    DECLARE _total_rec INT DEFAULT 0;
    DECLARE _total_pages INT DEFAULT 1;

    SET _offset = fGetOffset(PPage_Num, PNum_Rec);

    IF PEntity_Type = ''
    THEN SET PEntity_Type = NULL; END IF;

    IF PState_ID = 0
    THEN SET PState_ID = NULL; END IF;

    IF PCity_ID = 0
    THEN SET PCity_ID = NULL; END IF;

    SET _offset = fGetOffset(PPage_Num, PNum_Rec)