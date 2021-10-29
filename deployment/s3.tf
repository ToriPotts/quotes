resource "aws_s3_bucket" "code_storage" {
  bucket = "code-storage"
  acl    = "private"
}

resource "aws_s3_bucket_object" "v2_jar_file" {
  bucket = aws_s3_bucket.code_storage.id
  key    = "beanstalk/v2_jar"
  source = "../target/quotes-0.0.2-SNAPSHOT.jar"
}

