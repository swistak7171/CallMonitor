Module datasource

## Description

This module contains data sources for the functionalities of the application. The data sources are
responsible for retrieving, saving or modifying data. Thanks to the layered architecture, the data
sources are independent of the rest of the application. This allows the data sources to be easily
replaced by other data sources. For example, the current data sources are mostly "in memory" data
sources. This means that the data is stored in memory and is therefore not persistent. Such decision
was made to meet the requirements of the project. However, if the requirements change, the data
sources can be easily replaced by other data sources, such as "database" data sources.