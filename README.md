# Measuring-Robustness-Against-Input-Validation-Attacks

This tool and work is an extended version of my Master thesis and this work has been published in Software Quality Journal under the topic Estimating Software Robustness in Relation to Input Validation Vulnerabilities using Bayesian networks. The tool analyzes the control-flow graph (CFG) of a given JavaScript source code. The CFG is generated from an open source tool called [a TAJS](https://github.com/cs-au-dk/TAJS). Based on the analysis the variables that are not validated are detected. In addition, if the variables are validated the analysis checks if there are any precautions taken againts for six types of input validation attacks (XSS, SQL Injection etc.).

The obtained input validation information and input validation vulnerability attack statistics are used in the Bayesian Network for measuring the robustness of the given JavaScript code. For the Bayesian Network calculation and prediction, an open source project called OpenMarkov is used.

To run the tool:

You need to run the MainIV class.

The MainIV receives 2 arguments.
1) The first argument is the JavaScript source code to be analyzed.
2) The second argument is the "-flowgraph", which is necessary to generate CFG by TAJS for input validations analysis.
