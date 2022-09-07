
def dockerCluster (comp, path, version, archive) {
    withCredentials([usernamePassword(credentialsId: 'HARBOR_CRED', usernameVariable: 'HARBOR_USR', passwordVariable: 'HARBOR_PASS')]) {
     docker.withRegistry('https://registry.gitlab.com/abdglobal/devops', 'DEVOPS-TOKEN-1') {
            sh """
            docker build -t ${comp}:${version} -f ${path}/${comp}.Dockerfile ${path} --no-cache 
            docker login -u $HARBOR_USR -p $HARBOR_PASS registry.abdglobal.com
            docker tag ${comp}:${version} registry.abdglobal.com/abdglobal/${comp}:${version}
            docker push registry.gitlab.com/abdglobal/devops/${comp}:${version}
            """
        }
    }
}
