
CREATE TABLE Jwt_Users (
    USERNAME NVARCHAR(50) NOT NULL PRIMARY KEY,
    PASSWORD NVARCHAR(255) NOT NULL,  -- Sufficient length for hashed passwords
    ROLE NVARCHAR(20) NOT NULL,       -- Typically roles are short (USER, ADMIN, etc.)
    CREATE_DATE DATETIME2 NOT NULL,
    EXPIRE_DATE DATETIME2 NOT NULL,
    ACC_LOCK NCHAR(1) NOT NULL,       -- Single character for Y/N flag
    REQUESTER NVARCHAR(50) NULL,      -- Assuming requester is a username or identifier
    
    -- Optional constraints
    CONSTRAINT CHK_AccLock CHECK (ACC_LOCK IN ('Y', 'N')),
    CONSTRAINT CHK_Role CHECK (ROLE IN ('USER', 'ADMIN', 'MODERATOR')) -- Adjust roles as needed
);

-- Optional index on frequently queried columns
CREATE INDEX IX_Jwt_Users_Role ON Jwt_Users(ROLE);
CREATE INDEX IX_Jwt_Users_ExpDate ON Jwt_Users(EXPIRE_DATE);

-- Create Students table (parent table)
CREATE TABLE Students (
    ID BIGINT IDENTITY(1,1) PRIMARY KEY,
    NAME NVARCHAR(100) NOT NULL,
    DOB DATE NOT NULL,
    GENDER NCHAR(1) NOT NULL,
    MOBILE_NO NVARCHAR(15) NOT NULL,
    
    -- Constraints
    CONSTRAINT CHK_Gender CHECK (GENDER IN ('M', 'F')),
    CONSTRAINT CHK_MobileNo CHECK (MOBILE_NO NOT LIKE '%[^0-9+]%'),
    CONSTRAINT CHK_DOB CHECK (DOB <= CAST(GETDATE() AS DATE))
);

-- Create StudentDetail table (child table) with foreign key
CREATE TABLE Student_Detail (
    ID BIGINT IDENTITY(1,1) PRIMARY KEY,
    STUDENT_ID BIGINT NOT NULL,
    GRADE NVARCHAR(10) NOT NULL,
    PARENT_CONTACT NVARCHAR(20) NOT NULL,
    ENROLL_DATE DATETIME2 NOT NULL,
    ISSUE_COUNT INT DEFAULT 0 NOT NULL,
    
    -- Foreign key constraint with ON DELETE CASCADE
    CONSTRAINT FK_StudentDetail_Student FOREIGN KEY (STUDENT_ID) 
        REFERENCES Students(ID) ON DELETE CASCADE,
    
    -- Unique constraint ensures one-to-one relationship
    CONSTRAINT UQ_StudentDetail_Student UNIQUE (STUDENT_ID),
    
    -- Other constraints
    CONSTRAINT CHK_Grade CHECK (GRADE IN ('1', '2', '3', '4', '5', '6', 
                                       '7', '8', '9', '10', '11', '12')),
    CONSTRAINT CHK_IssueCount CHECK (ISSUE_COUNT >= 0),
    CONSTRAINT CHK_ParentContact CHECK (PARENT_CONTACT NOT LIKE '%[^0-9+]%')
);

-- Create indexes for performance
CREATE INDEX IX_Students_Name ON Students(NAME);
CREATE INDEX IX_Students_DOB ON Students(DOB);
CREATE INDEX IX_Student_Detail_StudentID ON Student_Detail(STUDENT_ID);
CREATE INDEX IX_Student_Detail_Grade ON Student_Detail(GRADE);
CREATE INDEX IX_Student_Detail_EnrollDate ON Student_Detail(ENROLL_DATE);

