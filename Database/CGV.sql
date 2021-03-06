USE [CGV]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 7/4/17 11:33:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Customer](
	[name] [nvarchar](100) NOT NULL,
	[passport] [nvarchar](50) NULL,
	[dob] [nvarchar](10) NULL,
	[phoneNumber] [nvarchar](20) NULL,
	[email] [varchar](50) NULL,
	[score] [varchar](50) NOT NULL,
	[accountType] [nvarchar](10) NOT NULL,
	[gender] [nvarchar](10) NULL,
	[cardID] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cardID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Staff]    Script Date: 7/4/17 11:33:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Staff](
	[username] [varchar](255) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[fullname] [nvarchar](255) NULL,
	[dob] [varchar](10) NULL,
	[email] [varchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[phone_num] [varchar](50) NULL,
	[isManager] [char](1) NOT NULL,
	[gender] [nchar](10) NULL,
	[isBlock] [char](1) NOT NULL,
 CONSTRAINT [PK_Staff] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
