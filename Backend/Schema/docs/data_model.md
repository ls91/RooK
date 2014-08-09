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

## Comments Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|ID             |INT AUTO INCREMENT|               |ID of the comment                                                     |
|COMMENT        |VARCHAR(1024)     |               |Comment added agaist object                                           |

## Board-Category Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|BOARD_ID       |INT               |BOARD.ID       |ID of the board                                                       |
|CATEGORY_ID    |INT               |CATEGORY.ID    |IDs of the categories that can exit for this board                    |

## Board-User Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|BOARD_ID       |INT               |BOARD.ID       |ID of the board                                                       |
|USER_ID        |INT               |USER.ID        |IDs of the users that can access the board                            |

## Category-Card Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|CATEGORY_ID    |INT               |CATEGORY.ID    |ID of the category                                                    |
|CARD_ID        |INT               |CARD.ID        |ID of the cards that belong in this category                          |

## Card-Tasks Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|CARD_ID        |INT               |CARD.ID        |ID of the card                                                        |
|TASK_ID        |INT               |TASKS.ID       |IDs of the tasks that belong to this card                             |

## Card-Comments Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|CARD_ID        |INT               |CARD.ID        |ID of the card                                                        |
|COMMENT_ID     |INT               |COMMENTS.ID    |Comments added agaist a card                                          |

## Card-Tags Table
|Column Name    |Column Type       |Foreign Key    |Comments                                                              |
| ------------- | ---------------- | ------------- | -------------------------------------------------------------------- |
|CARD_ID        |INT               |CARD.ID        |ID of the card                                                        |
|TAG_ID         |INT               |TAG.ID         |IDs of the tags associated with this card                             |