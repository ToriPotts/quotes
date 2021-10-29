# Elastic Beanstalk Deployment

This terraform deployment folder will grab a specified jar file from the target folder of the spring boot service and store it as an object in an s3 bucket.
The deployment will then kick off an Elastic Beanstalk pipeline that will spin up an ec2 instance to run the jar file and an elastic load balancer to route traffic to the ec2.

