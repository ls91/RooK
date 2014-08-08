# Data Model

## User Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|ID             |INT AUTO_INCREMENT|               |Unique ID for each user                                               |
|NAME           |VARCHAR(255)      |               |Full Name of the user                                                 |
|EMAIL          |VARCHAR(255)      |               |User email address for login                                          |
|PWD_HASH       |VARCHAR(1024)     |               |The users password stored as a hash                                   |

## Board Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|ID             |INT AUTO_INCREMENT|               |Unique ID for each board                                              |
|OWNER          |INT               |USER.ID        |Identifies the owner of the board                                     |
|TITLE          |VARCHAR(512)      |               |The title for the board                                               |
|DESCRIPTION    |VARCHAR(1024)     |               |A description for the board                                           |

## Category Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|ID             |INT AUTO_INCREMENT|               |Unique ID for each category                                           |
|TITLE          |VARCHAR(255)      |               |A title for the category                                              |
|MAX_CARDS      |INT               |               |Stores the maximum number of cards for the category (null if infinite)|

## Card Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|ID             |INT AUTO_INCREMENT|               |Unique ID for each card                                               |
|TITLE          |VARCHAR(255)      |               |A title for the card                                                  |
|DESCRIPTION    |VARCHAR(1024)     |               |A description for the card                                            |
|ASSIGNEE       |INT               |USER.ID        |Identifies the user assigned to the card                              |

## Task Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|ID             |INT AUTO_INCREMENT|               |Unique ID for each task                                               |
|DESCRIPTION    |VARCHAR(1024)     |               |A description for the task                                            |
|COMPLETE       |VARCHAR(1)        |               |Identifies a task as being completed or not ('Y' or 'N')              |

## Tags Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|ID             |INT AUTO_INCREMENT|               |Unique ID for each task                                               |
|TAG            |VARCHAR(20)       |               |A name for the tag                                                    |

## Board-Category Table

## Board-User Table

## Category-Card Table

## Card-Tasks Table

## Card-Comments Table

## Card-Tags Table
