
resource "aws_elastic_beanstalk_application" "quotes_api" {
  name        = "quotes_api"
  description = "The description of my application"
}

resource "aws_elastic_beanstalk_application_version" "quotes_version_2" {
  application = aws_elastic_beanstalk_application.quotes_api.name
  bucket      = aws_s3_bucket.code_storage.id
  key         = aws_s3_bucket_object.v2_jar_file.id
  name        = "myapp-2.0.0"
}

resource "aws_elastic_beanstalk_environment" "beanstalk_myapp_env" {
  name                = "myapp-prod"
  application         = aws_elastic_beanstalk_application.quotes_api.name
  solution_stack_name = "64bit Amazon Linux 2 v3.2.7 running Corretto 11"
  version_label       = aws_elastic_beanstalk_application_version.quotes_version_2.name

  setting {
    name      = "SERVER_PORT"
    namespace = "aws:elasticbeanstalk:application:environment"
    value     = "5000"
  }

  setting {
    namespace = "aws:ec2:instances"
    name      = "InstanceTypes"
    value     = "t2.micro"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = "aws-elasticbeanstalk-ec2-role"
  }
}