-- Delete
DROP PROCEDURE IF EXISTS sStateDelete;
CREATE PROCEDURE sStateDelete
  (
    IN pStateID INT
  )
  BEGIN
    DELETE FROM tState
    WHERE StateID = pStateID;
	
	SELECT
      1    AS IS_SUCCESS,
      pSemesterID  AS TRANSACTION_ID,
      ''   AS PRIMARY_COLUMN,
      NULL AS DB_RESPONSE_CODE,
      NULL AS DB_RESPONSE_MESSAGE
    FROM dual;
  END;
