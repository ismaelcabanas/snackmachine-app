INSERT INTO SNACK_MACHINE(SM_ID, SM_ONE_CENT_COUNT, SM_TEN_CENT_COUNT, SM_QUARTER_CENT_COUNT, SM_ONE_DOLLAR_COUNT, SM_FIVE_DOLLAR_COUNT, SM_TWENTY_DOLLAR_COUNT) VALUES ('1', 0, 0, 0, 0, 0, 0);

INSERT INTO SNACK(S_ID, S_NAME) VALUES ('1', 'Chocolat');
INSERT INTO SNACK(S_ID, S_NAME) VALUES ('2', 'Coke');
INSERT INTO SNACK(S_ID, S_NAME) VALUES ('3', 'Gums');

INSERT INTO SLOT (SL_ID, SL_SNACK_MACHINE_ID, SL_SNACK_ID, SL_QUANTITY, SL_POSITION, SL_PRICE) VALUES ('1', '1', '1', 10, 1, 1.00);
INSERT INTO SLOT (SL_ID, SL_SNACK_MACHINE_ID, SL_SNACK_ID, SL_QUANTITY, SL_POSITION, SL_PRICE) VALUES ('2', '1', '2', 10, 1, 0.75);
INSERT INTO SLOT (SL_ID, SL_SNACK_MACHINE_ID, SL_SNACK_ID, SL_QUANTITY, SL_POSITION, SL_PRICE) VALUES ('3', '1', '3', 10, 1, 0.5);