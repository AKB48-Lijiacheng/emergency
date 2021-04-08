def label = "slave-${UUID.randomUUID().toString()}"


podTemplate(label: label, containers: [
  containerTemplate(name: 'maven', image: 'maven:3.6-alpine', command: 'cat', ttyEnabled: true),
  containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
  containerTemplate(name: 'kubectl', image: 'cnych/kubectl', command: 'cat', ttyEnabled: true)
  //containerTemplate(name: 'helm', image: 'cnych/helm', command: 'cat', ttyEnabled: true)
], volumes: [
  hostPathVolume(mountPath: '/root/.m2', hostPath: '/root/.m2'),
  hostPathVolume(mountPath: '/usr/share/maven/conf/settings.xml', hostPath: '/root/settings.xml'),
  hostPathVolume(mountPath: '/root/.kube', hostPath: '/root/.kube'),
  hostPathVolume(mountPath: '/opt/kubernetes/ssl', hostPath: '/opt/kubernetes/ssl'),
  hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock')
]) {
  node(label) {
    def module="emergency-back"
    def dockerRegistryUrl = "10.36.11.50:8081"
    def imageEndpoint = "emergency/${module}"
    def image = "${dockerRegistryUrl}/${imageEndpoint}"

    stage('Prepare') {
        echo "1.Prepare Stage"
        checkout scm
        // def myRepo = checkout scm
        // def gitCommit = myRepo.GIT_COMMIT
        // def gitBranch = myRepo.GIT_BRANCH
        //echo "gitBranch==${gitBranch}"
        script {
            //获取代码提交码
            imageTag = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
            echo "imageTag == ${imageTag}"
            // master分支为null
            if (env.BRANCH_NAME != null) {
                echo "env.BRANCH_NAME====${env.BRANCH_NAME}"
                imageTag = "${env.BRANCH_NAME}-${imageTag}"
            }
        }
    }
    stage('单元测试') {
      echo "测试阶段"
    }
    stage('代码编译打包') {
      try {
        container('maven') {
          echo "2. 代码编译打包阶段"
          sh """
            mvn install -Dmaven.test.skip=true
          """
        }
      } catch (exc) {
        println "构建失败 - ${currentBuild.fullDisplayName}"
        throw(exc)
      }
    }
    stage('构建 Docker 镜像') {
      withCredentials([[$class: 'UsernamePasswordMultiBinding',
        credentialsId: 'harborHub',
        usernameVariable: 'user',
        passwordVariable: 'passwd']]) {
          container('docker') {
            echo "3. 构建 Docker 镜像阶段"
            sh """
              sed -i "s@<MODULE>@${module}@g" ${module}/Dockerfile
              cat ${module}/Dockerfile
              docker login ${dockerRegistryUrl} -u ${user} -p ${passwd}
              docker build -t ${image}:${imageTag} -f ${module}/Dockerfile ${module}
              docker push ${image}:${imageTag}
              """
          }
        }
    }
    stage('运行 Kubectl') {
      container('kubectl') {
        echo "查看 K8S 集群 Pod 列表"
        sh "kubectl get pods"


        sh """
          sed -i "s@<IMAGE>@${image}@" k8s.yaml
          sed -i "s@<MODULE>@${module}@" k8s.yaml
          sed -i "s@<IMAGE_TAG>@${imageTag}@" k8s.yaml
          cat ${module}/k8s.yaml
          kubectl apply -f ${module}/k8s.yaml
        """
      }
    }
  }
}