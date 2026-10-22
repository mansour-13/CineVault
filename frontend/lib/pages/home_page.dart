import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('CineVault')),
      body: Column(
        children: [
          ListTile(
            leading: Icon(Icons.movie),
            title: Text('My Movie'),
            trailing: Icon(Icons.chevron_right),
            onTap: () {},
          ),
          Text('My Movies'),
          Text('TV Series'),
          Text('Add Movie'),
          Text('Statistics'),
        ],
      ),
    );
  }
}
