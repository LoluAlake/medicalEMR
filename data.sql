use medicalemr;

INSERT INTO address VALUES (1,'47 MySakila Drive',NULL,'Alberta','Alberta','12345','USA'),
(2,'23 Workhaven Lane',NULL,'Alberta','Alberta','12345','USA'),
(3,'1913 Hanoi Way',NULL,'Nagasaki','Nagasaki','35200','USA'),
(4,'1121 Loja Avenue','','California','California','17886','USA'),
(5,'692 Joliet Street','','Attika','Attika','83579','USA'),
(6,'1566 Inegl Manor','','Mandalay','Mandalay','53561','USA'),
(7,'53 Idfu Parkway','','Nantou',361,'42399','USA'),
(8,'1795 Santiago de Compostela Way','','Texas','Texas','81289','USA'),
(9,'900 Santiago de Compostela Parkway','','Central Serbia','Cental Serbia','93896','Serbia'),
(10,'419 Iligan Lane','','Madhya Pradesh','Madhay Pradesh','72878','India');

INSERT INTO Doctor VALUES(1,'KATHLEEN','ADAMS',4),
(2,'JULIE','SANCHEZ',2),
(3,'ANDY','NOLEN',7);


INSERT INTO PATIENT VALUES(1,454232,1,'HEATHER','MORRIS','1970-04-06','F',69,180,
	'heather@gmailc.om','980-127-3564','Married',9088761264,'Andrew Morris','879-456-1453','Employed','no','no','','2020-11-20',0),
(2,7843923,2,'ALICE','STEWART','1950-09-10','f',60,145,'ALICE.STEWART@sakilacustomer.org',
	'901-345-9232','Married',9807676545,'David Stewart','456-234-1245','Retired','no','no','','2020-11-20',0),
(3,2872323,3,'DIANE','COLLINS','1999-2-9','F',72,150,'DIANE.COLLINS@sakilacustomer.org',
	'349-123-8908','Un-married',32321289011,'Roxy Collins','343-908-6789','Employed','no','no','','2020-11-20',0),
(4,2323232,4,'DORIS','REED','2002-09-24','F',55,210,'DORIS.REED@sakilacustomer.org',
	'423-890-4675','Un-married',2323212123,'Monica REED','435-098-9807','Student','no','no','','2020-11-20',0),
(5,343412490,5,'GLORIA','COOK','2010-10-12','F',65,170,'GLORIA.COOK@sakilacustomer.org',
	'234-890-2323','Un-married',8901229001,'Reid Cook','678-087-1283','Student','no','no','','2020-11-20',0);

INSERT INTO PROCEDURES VALUES(1,90653,'Influenza Vaccine, inactivated (IIV), subunit, adjuvanted, for intramuscular use',
	'Flu',20.00,0),
	(2,84439,'Thyroid Panel with TSH ','Thyroid blood work',40.00,0),
	(3,82947,'Glucose, quantitative , blood (except reagent strip)','Diabetes Check',67.90,0),
	(4,11042,'Debridement, subcutaneous tissue (includes epidermis and dermis, if performed)','Foot Ulcer',45.50,0),
	(5)


