            stage('Trigger Branch Build') {
                steps {
                    script {
                        
                            echo "${uploadedAgentsPaths}"
                            for (String uploadedAgent : uploadedAgentsPaths) {
                                try {
                                    echo "IN FOR LOOP"
                                    echo "${uploadedAgent}"
                                    Pattern pattern = Pattern.compile("/repository/(.*)\\.tgz")
                                    Matcher matcher  = pattern.matcher(uploadedAgent)
                                    Boolean found = matcher.find()
                                    String agent  = matcher.group()
                                    echo "${agent}"
                                    build job: "Automation/Agents_QA_Automation_Dev", parameters: [string(name: 'AGENTS_FILES', value:"http://192.168.2.47:8081${agent}")]
                            
                                }
                                catch (Exception e){
                                        logger.logError("Failed: " + path  + " ${uploadedAgentsPaths}")
                                }
                            }
                            
                }
            }
        }
