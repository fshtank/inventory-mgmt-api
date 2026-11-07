# Hahsi Vault: Does AWS have its own equivalent?

## Yes — AWS has two managed secrets stores
### . . . plus the key-management layer underneath:


| AWS service | Analog to | Notes |
|---|---|---|
| AWS Secrets Manager | Vault's secrets engine / KV | Purpose-built for DB passwords, API keys, app secrets. Automatic rotation, full audit trail via CloudTrail. |
| SSM Parameter Store (SecureString type) | A lighter, cheaper secret store | Cheaper than Secrets Manager; rotation only via Lambda. Good for config + simple secrets. |
| AWS KMS | Vault Transit / key mgmt | The encryption layer behind both of the above — manages the KEKs, doesn't store the secret values themselves. |   


For your app going to the cloud, the typical pattern is: store JWT_SECRET and the RDS password in **Secrets Manager**, and let the app's IAM role (on EC2/ECS/Lambda) fetch them at startup — so no secret ever lives in application-prod.yml or an env var baked into an image. You can keep your current SPRING_DATASOURCE_* env-var design and just populate those vars from Secrets Manager (via the aws-secretsmanager property source for Spring, or a Lambda/SSM fetch at boot).

One nuance worth knowing: AWS deliberately does not offer a full Vault clone (no self-hosted engine, no arbitrary secrets plugins). If you want Vault's model (dynamic secrets, lease-based, audit) on AWS, people run Vault Enterprise on AWS or use Infisical/Doppler instead. For most apps, Secrets Manager is enough.