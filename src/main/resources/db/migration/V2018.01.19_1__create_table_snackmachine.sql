﻿CREATE TABLE IF NOT EXISTS SNACK_MACHINE
( SM_ID VARCHAR(255) NOT NULL,
  SM_ONE_CENT_COUNT INT,
  SM_TEN_CENT_COUNT INT,
  SM_QUARTER_CENT_COUNT INT,
  SM_ONE_DOLLAR_COUNT INT,
  SM_FIVE_DOLLAR_COUNT INT,
  SM_TWENTY_DOLLAR_COUNT INT,
  CONSTRAINT PK_SNACK_MACHINE PRIMARY KEY (SM_ID));