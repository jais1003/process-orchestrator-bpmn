insert into SERVICE_REGISTRY values(1,'S1','Entity Profile Service','http://localhost:8080/service/entityprofile/invoke','');
insert into SERVICE_REGISTRY values(1,'S2','Entity Hierarchy Service','http://localhost:8080/service/entityhierarchy/invoke','');
insert into SERVICE_REGISTRY values(1,'S3','Entity Validation Service','http://localhost:8080/service/entityvalidation/invoke','');
insert into SERVICE_REGISTRY values(1,'S4','KYC Profile Service','http://localhost:8080/service/kycprofile/invoke','KYC');
insert into SERVICE_REGISTRY values(1,'S5','Entity Unwrapping Service','http://localhost:8080/service/entityunwrapping/invoke','');
insert into SERVICE_REGISTRY values(1,'S6','ID&V Service','http://localhost:8080/service/idv/invoke','IDV');
insert into SERVICE_REGISTRY values(1,'S7','Screening Service','http://localhost:8080/service/screening/invoke','SCREENING');
insert into SERVICE_REGISTRY values(1,'S8','KYC Verification & Approval','http://localhost:8080/service/kycapprove/invoke','');
insert into SERVICE_REGISTRY values(1,'S9','Pre-Tax Service','http://localhost:8080/service/pretax/invoke','TAX');
insert into SERVICE_REGISTRY values(1,'S10','FATCA Service','http://localhost:8080/service/fatca/invoke','FATCA');
insert into SERVICE_REGISTRY values(1,'S11','CRS Service','http://localhost:8080/service/crs/invoke','CRS');

SELECT servicecode, version, servicename, serviceurl, type FROM SERVICE_REGISTRY;