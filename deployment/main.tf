terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.48.0"
    }
    random = {
      source  = "hashicorp/random"
      version = "~> 3.1.0"
    }
    archive = {
      source  = "hashicorp/archive"
      version = "~> 2.2.0"
    }
  }

  required_version = "~> 1.0"
}

provider "aws" {
  region = var.aws_region
}


resource "aws_s3_bucket" "s3_bucket_myapp" {
  bucket = "spring-boot-source"
  acl    = "private"
}

resource "aws_s3_bucket_object" "v1_jar_file" {
  bucket = aws_s3_bucket.s3_bucket_myapp.id
  key    = "beanstalk/myapp"
  source = "./quotes-0.0.1-SNAPSHOT.jar"
}

resource "aws_s3_bucket_object" "v2_jar_file" {
  bucket = aws_s3_bucket.s3_bucket_myapp.id
  key    = "beanstalk/v2_jar"
  source = "./quotes-0.0.2-SNAPSHOT.jar"
}



resource "aws_elastic_beanstalk_application" "beanstalk_myapp" {
  name        = "myapp"
  description = "The description of my application"
}

resource "aws_elastic_beanstalk_application_version" "beanstalk_myapp_version" {
  application = aws_elastic_beanstalk_application.beanstalk_myapp.name
  bucket      = aws_s3_bucket.s3_bucket_myapp.id
  key         = aws_s3_bucket_object.v1_jar_file.id
  name        = "myapp-1.0.0"
}
resource "aws_elastic_beanstalk_application_version" "beanstalk_myapp_version_2" {
  application = aws_elastic_beanstalk_application.beanstalk_myapp.name
  bucket      = aws_s3_bucket.s3_bucket_myapp.id
  key         = aws_s3_bucket_object.v2_jar_file.id
  name        = "myapp-2.0.0"
}

resource "aws_elastic_beanstalk_environment" "beanstalk_myapp_env" {
  name                = "myapp-prod"
  application         = aws_elastic_beanstalk_application.beanstalk_myapp.name
  solution_stack_name = "64bit Amazon Linux 2 v3.2.7 running Corretto 11"
  version_label       = aws_elastic_beanstalk_application_version.beanstalk_myapp_version_2.name

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


