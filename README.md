# Logger

A Logger infrastructure I have been asked to implement during a job interview.
The specifications for the infrastructure were as follows:

* Should be thread-safe
* Should be easily extended
* Should contain a monitoring capability: For example if 100 error messages were written to the log, an alert would be triggered. 
* The monitoring capability should also be easily extended
* Another implicit specification which was not said but I deduced was: 
  Ability to configure multiple different output targets (File, Console, etc.) easily.
